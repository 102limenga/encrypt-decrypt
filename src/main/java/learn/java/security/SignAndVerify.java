package learn.java.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名和验签
 */
public class SignAndVerify {


    public byte[] sign(PrivateKey priKey, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //添加算法NONEwithRSA,MD2/5withRSA,SHA1/224/256/384/512withRSA,RIPEMD128/160withRSA
        //DSA的算法则包括 SHA1/224/256/384/512withDSA
        //ECDSA的算法包括 NONEwithECDSA,SHA1/224/256/384/512withECDSA,RIPEMD160withECDSA
        Signature signature = Signature.getInstance("MD5withRSA");
        //初始化
        signature.initSign(priKey);
        //将原始数据填充进去
        signature.update(data.getBytes());
        //对刚刚填充的数据进行签名
        return signature.sign();
    }

    public boolean verify(PublicKey publicKey, byte[] data, byte[] sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        //原始数据
        signature.update(data);
        //利用原始数据，公钥，算法，对签名进行验证
        return signature.verify(sign);
    }

    public byte[] ECDSASign(byte[] data, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        //若需要实现ripemd160withECDSA算法，需要加入这个类的支持
        Security.addProvider(new BouncyCastleProvider());
        //转换私钥材料
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        //实例化密钥工厂，也可以是RSA,DSA,ECDSA
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA");
        //取私钥对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //实例化Signature
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(priKey);
        signature.update(data);
        return signature.sign();
    }

    public boolean verify(byte[] data, byte[] publicKey, byte[] sign) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        //转换公钥材料
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        //也可以是DSA,ECDSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        return verify(pubKey, data, sign);
    }

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        //实例化密钥对生成器，也可以是DSA,ECDSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //初始化密钥对生成器，长度必须是64的倍数，范围在512~65536之间
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Map keyMap = new HashMap();
        keyMap.put("pubKey", keyPair.getPublic());
        keyMap.put("priKey", keyPair.getPrivate());
        return keyMap;
    }
}
