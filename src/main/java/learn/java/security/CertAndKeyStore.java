package learn.java.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertAndKeyStore {

    public static String keyStorePath = "C:" + File.pathSeparator + "tmp" + File.pathSeparator + "ccs.keystore";
    public static char[] password = "123456".toCharArray();
    public static String alias = "www.ccs.org";
    public static String certificatePath = "C:" + File.pathSeparator + "tmp" + File.pathSeparator + "ccs.cer";

    /**
     * 获取KeyStore
     *
     * @return
     */
    public static KeyStore getKeyStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        //双向证书时，类型也可能填入 PKCS12
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream inputStream = new FileInputStream(keyStorePath);
        keyStore.load(inputStream, password);
        inputStream.close();
        return keyStore;
    }

    public static KeyStore getKeyStore(String keyStorePath, char[] password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        return getKeyStore(keyStorePath, password);
    }

    public static PrivateKey getPrivateKey() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException {
        KeyStore keyStore = getKeyStore();
        return (PrivateKey) keyStore.getKey(alias, password);
    }

    public static X509Certificate getCert() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        return (X509Certificate) getKeyStore().getCertificate(alias);
    }

    public static Signature getSignature() throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException {
        X509Certificate certificate = getCert();
        return Signature.getInstance(certificate.getSigAlgName());
    }

    public static Certificate getCertificate() throws CertificateException, IOException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        FileInputStream fi = new FileInputStream(certificatePath);
        Certificate certificate = certificateFactory.generateCertificate(fi);
        fi.close();
        return certificate;
    }

    /**
     * 使用publicKey或者privateKey进行加密
     *
     * @param key  可以传入PublicKey或PrivateKey
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encryptByPrivateOrPublicKey(Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 使用publicKey或者privateKey进行解密
     *
     * @param key  可以传入PublicKey或PrivateKey
     * @param data
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decryptByPrivateOrPublicKey(Key key, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }


}
