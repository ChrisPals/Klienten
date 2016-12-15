package encrypters;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Christofferpalsgaard on 24/11/2016.
 */
public class Digester {
    private final static String SALT = "82efbcc2cc33d33cdadf12806d75591a";
    private static MessageDigest digest;


    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public String hash(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("fejl");

        }

        str = str + Digester.SALT;
        return Digester._hash(str);
    }
    private static String hashWithSalt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("fejl");

        }
        str = str + Digester.SALT;

        return Digester._hash(str);
    }
    private static String _hash(String str) {
        digest.update(str.getBytes());
        byte[] hash = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte ahash : hash) {
            if((0xff & ahash) <0x10) {
                hexString.append("0" + Integer.toHexString(0xff & ahash));
            } else {
            hexString.append(Integer.toHexString(0xFF & ahash));

            }
        }
        return hexString.toString();
    }
}
