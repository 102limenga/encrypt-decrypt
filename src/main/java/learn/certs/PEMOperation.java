package learn.certs;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;

import java.io.File;
import java.security.KeyPair;
import java.security.Security;

public class PEMOperation {


    static{
        Security.addProvider(new BouncyCastleProvider());
    }

//    public static KeyPair readKeyPair(File pemFile, char[] password) {
//        PEMParser pemParser = new PEMParser();
//    }
}
