package learn.java.crypto;

import vo.Student;

import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 对象加解密处理
 */
public class SealedObjectLearn {

    public static Student sealedStudent() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
        Student zzy = new Student("zzy");
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        SecretKey key = kg.generateKey();
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,key);
        SealedObject object = new SealedObject(zzy, cipher);
        Cipher cipher2 = Cipher.getInstance(key.getAlgorithm());
        cipher2.init(Cipher.DECRYPT_MODE,key);
        return (Student) object.getObject(cipher2);
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, ClassNotFoundException {
        System.out.println(sealedStudent());
    }
}
