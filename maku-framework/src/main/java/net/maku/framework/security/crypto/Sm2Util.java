package net.maku.framework.security.crypto;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 国密SM2加密算法
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class Sm2Util {
    /**
     * 公钥
     */
    private final static String PUBLIC_KEY = "3059301306072a8648ce3d020106082a811ccf5501822d034200040a302b5e4b961afb3908a4ae191266ac5866be100fc52e3b8dba9707c8620e64ae790ceffc3bfbf262dc098d293dd3e303356cb91b54861c767997799d2f0060";
    /**
     * 私钥
     */
    private final static String PRIVATE_KEY = "308193020100301306072a8648ce3d020106082a811ccf5501822d047930770201010420d7840173df3d6cd72cad4040dfc7dbfcde539f5b490b54f3cd5c4125544b38aea00a06082a811ccf5501822da144034200040a302b5e4b961afb3908a4ae191266ac5866be100fc52e3b8dba9707c8620e64ae790ceffc3bfbf262dc098d293dd3e303356cb91b54861c767997799d2f0060";

    private final static SM2 sm2;

    static {
        sm2 = SmUtil.sm2(PRIVATE_KEY, PUBLIC_KEY);
    }

    /**
     * 加密
     *
     * @param data 明文
     * @return 加密后的密文
     */
    public static String encrypt(String data) {
        return sm2.encryptBase64(data, KeyType.PublicKey);
    }

    /**
     * 解密
     *
     * @param data 加密后的密文
     * @return 解密后的明文
     */
    public static String decrypt(String data) {
        return sm2.decryptStr(data, KeyType.PrivateKey);
    }

    public static void main(String[] args) {
        KeyPair keyPair = SecureUtil.generateKeyPair("SM2");
        System.out.println("privateKey：" + HexUtil.encodeHexStr(keyPair.getPrivate().getEncoded()));
        System.out.println("publicKey：" + HexUtil.encodeHexStr(keyPair.getPublic().getEncoded()));

        PublicKey publicKey = keyPair.getPublic();
        if (publicKey instanceof BCECPublicKey) {
            // 获取65字节非压缩缩的十六进制公钥串(0x04)
            String publicKeyHex = Hex.toHexString(((BCECPublicKey) publicKey).getQ().getEncoded(false));
            System.out.println("SM2公钥：" + publicKeyHex);
        }
        PrivateKey privateKey = keyPair.getPrivate();
        if (privateKey instanceof BCECPrivateKey) {
            // 获取32字节十六进制私钥串
            String privateKeyHex = ((BCECPrivateKey) privateKey).getD().toString(16);
            System.out.println("SM2私钥：" + privateKeyHex);
        }

        String password = "admin";
        String sm2Password = Sm2Util.encrypt(password);
        System.out.println("sm2 加密:" + sm2Password);
        System.out.println("sm2 解密:" + Sm2Util.decrypt(sm2Password));


        System.out.println("sm3 解密:" + SmUtil.sm3("admin"));
    }
}
