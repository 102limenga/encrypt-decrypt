package learn.java.security;

import java.security.Key;

public class Base {

    public static void main(String[] args) {
        Key key = new Key() {
            public String getAlgorithm() {
                return null;
            }

            public String getFormat() {
                return null;
            }

            public byte[] getEncoded() {
                return new byte[0];
            }
        };
        key.getEncoded();

    }


}
