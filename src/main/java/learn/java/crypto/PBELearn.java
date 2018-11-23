package learn.java.crypto;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class PBELearn {
    //长度为8字节的盐
    private static byte[] salt = new SecureRandom().generateSeed(8);

    public static Key toKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PEBWITHMD5andDES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    public static byte[] encrypt(byte[] data, String password) throws InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        Key key = toKey(password);
        //100是迭代次数，即构成密钥时候的算法复杂度
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
        Cipher cipher = Cipher.getInstance("PEBWITHMD5andDES");
        cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
        return cipher.doFinal(data);
    }
}
