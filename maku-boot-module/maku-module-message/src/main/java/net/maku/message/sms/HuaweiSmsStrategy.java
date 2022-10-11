package net.maku.message.sms;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import lombok.Data;
import net.maku.framework.common.exception.ServerException;
import net.maku.framework.common.utils.JsonUtils;
import net.maku.message.sms.config.SmsConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import javax.net.ssl.*;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 华为云短信
 *
 * @author 阿沐 babamu@126.com
 */
public class HuaweiSmsStrategy implements SmsStrategy {
    // 无需修改，用于格式化鉴权头域，给"X-WSSE"参数赋值
    private static final String WSSE_HEADER_FORMAT = "UsernameToken Username=\"%s\",PasswordDigest=\"%s\",Nonce=\"%s\",Created=\"%s\"";
    // 无需修改，用于格式化鉴权头域，给"Authorization"参数赋值
    private static final String AUTH_HEADER_VALUE = "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"";
    private final SmsConfig smsConfig;

    public HuaweiSmsStrategy(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    @Override
    public void send(String mobile, Map<String, String> params) {
        // 有参数则设置
        String templateParas = null;
        if (MapUtil.isNotEmpty(params)) {
            templateParas = JsonUtils.toJsonString(params.values().toArray(new String[0]));
        }

        // 请求Body,不携带签名名称时,signature请填null
        String body = buildRequestBody(smsConfig.getSenderId(), "+86" + mobile, smsConfig.getTemplateId(), templateParas, null, smsConfig.getSignName());
        if (StringUtils.isBlank(body)) {
            throw new ServerException("body is null.");
        }

        // 请求Headers中的X-WSSE参数值
        String wsseHeader = buildWsseHeader(smsConfig.getAccessKey(), smsConfig.getSecretKey());
        if (StringUtils.isBlank(wsseHeader)) {
            throw new ServerException("wsse header is null.");
        }

        try {
            // 使用 https
            trustAllHttpsCertificates();

            // 接入地址
            String url = smsConfig.getUrl() + "/sms/batchSendSms/v1";
            URL realUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) realUrl.openConnection();
            HostnameVerifier hv = (hostname, session) -> true;
            connection.setHostnameVerifier(hv);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Authorization", AUTH_HEADER_VALUE);
            connection.setRequestProperty("X-WSSE", wsseHeader);
            connection.connect();

            IoUtil.writeUtf8(connection.getOutputStream(), true, body);

            int status = connection.getResponseCode();
            if (status == HttpStatus.OK.value()) {
                String response = IoUtil.read(connection.getInputStream(), CharsetUtil.CHARSET_UTF_8);
                HuaweiSmsResult result = JsonUtils.parseObject(response, HuaweiSmsResult.class);

                // 短信是否发送成功
                assert result != null;
                if (!"000000".equals(result.code)) {
                    throw new ServerException(result.description);
                }
            } else { //400 401
                throw new ServerException(IoUtil.read(connection.getErrorStream(), CharsetUtil.CHARSET_UTF_8));
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    /**
     * 构造请求Body体
     *
     * @param signature | 签名名称,使用国内短信通用模板时填写
     */
    static String buildRequestBody(String sender, String receiver, String templateId, String templateParas,
                                   String statusCallBack, String signature) {
        if (null == sender || null == receiver || null == templateId || sender.isEmpty() || receiver.isEmpty()
                || templateId.isEmpty()) {
            throw new ServerException("buildRequestBody(): sender, receiver or templateId is null.");
        }
        Map<String, String> map = new HashMap<>();

        map.put("from", sender);
        map.put("to", receiver);
        map.put("templateId", templateId);
        if (null != templateParas && !templateParas.isEmpty()) {
            map.put("templateParas", templateParas);
        }
        if (null != statusCallBack && !statusCallBack.isEmpty()) {
            map.put("statusCallback", statusCallBack);
        }
        if (null != signature && !signature.isEmpty()) {
            map.put("signature", signature);
        }

        StringBuilder sb = new StringBuilder();
        String temp = "";

        for (String s : map.keySet()) {
            try {
                temp = URLEncoder.encode(map.get(s), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(s).append("=").append(temp).append("&");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 构造X-WSSE参数值
     */
    static String buildWsseHeader(String appKey, String appSecret) {
        if (null == appKey || null == appSecret || appKey.isEmpty() || appSecret.isEmpty()) {
            throw new ServerException("buildWsseHeader(): appKey or appSecret is null.");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        String nonce = UUID.randomUUID().toString().replace("-", "");

        MessageDigest md;
        byte[] passwordDigest = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update((nonce + time + appSecret).getBytes());
            passwordDigest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String passwordDigestBase64Str = Base64.getEncoder().encodeToString(passwordDigest);

        return String.format(WSSE_HEADER_FORMAT, appKey, passwordDigestBase64Str, nonce, time);
    }

    static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {

                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) {

                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    @Data
    static class HuaweiSmsResult {
        // code为000000，表示成功
        private String code;
        private String description;
        private List<Object> result;
    }
}
