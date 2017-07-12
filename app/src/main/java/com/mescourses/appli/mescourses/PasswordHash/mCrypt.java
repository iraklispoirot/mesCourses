package com.mescourses.appli.mescourses.PasswordHash;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class mCrypt {

/*  Exemple utilisation

    String password  = "password";

    int saltLength = 32; // same size as key output
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[saltLength];
        random.nextBytes(salt);

    TextView brol = (TextView) findViewById(R.id.brol) ;
    byte[] myPassword = mCrypt.hashPassword(password.toCharArray(),salt) ;
    */

    public static byte[] hashPassword(final char[] password, final byte[] salt) {

        int iterations = 1000;
        int keyLength = 256;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
