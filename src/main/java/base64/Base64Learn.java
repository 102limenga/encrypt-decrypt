package base64;

import org.apache.commons.codec.binary.Base64;

public class Base64Learn {

    public static void main(String[] args) {
        System.out.println(Base64.encodeBase64String("你好a".getBytes()));
    }
}
