package learn.java.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息摘要
 */
public class MessageDigestLearn {

    public static String digest(String message) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(message.getBytes());
        return Base64.encodeBase64String(sha.digest());
    }

    /**
     * 摘要算法流处理
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static byte[] digestWithInputStream(String message) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD");
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(message.getBytes()), md);
        dis.read(message.getBytes(), 0, message.getBytes().length);
        return dis.getMessageDigest().digest();
    }

    public static String digestByMac(byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        mac.update(data);
        return Base64.encodeBase64String(mac.doFinal());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        System.out.println(digest("sha"));
        System.out.println(digest("我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀"));
        System.out.println(digestByMac("我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀我我的的发发放大使的发生阿斯顿发大水发射点法大师傅啊手动阀手动阀手动阀第三方啊手动阀手动阀手动阀".getBytes()));
    }
}
