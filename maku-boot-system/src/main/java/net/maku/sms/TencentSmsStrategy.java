package net.maku.sms;

import cn.hutool.core.map.MapUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import net.maku.framework.common.constant.Constant;
import net.maku.framework.common.exception.ServerException;
import net.maku.sms.config.SmsConfig;

import java.util.Map;

/**
 * 腾讯云短信
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class TencentSmsStrategy implements SmsStrategy {
    private final SmsConfig smsConfig;
    private SmsClient client;

    public TencentSmsStrategy(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;

        try {
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setReqMethod("POST");
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            Credential cred = new Credential(smsConfig.getAccessKey(), smsConfig.getSecretKey());
            this.client = new SmsClient(cred, "ap-guangzhou", clientProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String mobile, Map<String, String> params) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSmsSdkAppId(smsConfig.getAppId());
        request.setSignName(smsConfig.getSignName());
        request.setTemplateId(smsConfig.getTemplateId());

        // 有参数则设置
        if (MapUtil.isNotEmpty(params)) {
            request.setTemplateParamSet(params.values().toArray(new String[0]));
        }

        // 手机号
        String[] phoneNumberSet = {"+86" + mobile};
        request.setPhoneNumberSet(phoneNumberSet);

        // 国际、港澳台短信，需要添加SenderId，国内短信填空，默认未开通
        request.setSenderId(smsConfig.getSenderId());

        try {
            // 发送短信
            SendSmsResponse response = client.SendSms(request);
            SendStatus sendStatus = response.getSendStatusSet()[0];

            // 发送失败
            if (!Constant.OK.equalsIgnoreCase(sendStatus.getCode())) {
                throw new ServerException(sendStatus.getMessage());
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
