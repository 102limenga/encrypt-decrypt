package learn.java.net.ssl;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static learn.java.security.CertAndKeyStore.*;

public class HttpsUrlConnectionLearn {

    public static final String protocol = "TLS";

    public static SSLSocketFactory getSSLSocketFactory(String trustStorePath) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, IOException, CertificateException, KeyManagementException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = getKeyStore();
        //初始化密钥工厂
        keyManagerFactory.init(keyStore, password);
        //-----------------------------------------------------------------------------
        //信任库工厂
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        //信任库
        KeyStore trustKeyStore = getKeyStore(trustStorePath, password);
        trustManagerFactory.init(trustKeyStore);
        //-----------------------------------------------------------------------------
        //实例化ssl上下文
        SSLContext ctx = SSLContext.getInstance(protocol);
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
        return ctx.getSocketFactory();
    }

    /**
     * 为HttpsURLConnection配置SSLSocketFactory
     *
     * @param conn
     * @param password
     * @param trustStorePath
     * @throws Exception
     */
    public static void configSSLSocketFactory(HttpsURLConnection conn, String password, String trustStorePath) throws Exception {
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(trustStorePath);
        conn.setSSLSocketFactory(sslSocketFactory);
    }

    /**
     * 打印HttpsURLConnection连接内容
     * @param conn
     * @throws IOException
     */
    public static void printConnData(HttpsURLConnection conn) throws IOException {
        int length = conn.getContentLength();
        byte[] data = null;
        if (length != -1) {
            DataInputStream dis = new DataInputStream(conn.getInputStream());
            data = new byte[length];
            dis.readFully(data);
            dis.close();
            System.out.println(new String(data));
        }
        conn.disconnect();
    }
}
