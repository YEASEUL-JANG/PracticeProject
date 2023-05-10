package portal.utils;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptDecryptUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncryptDecryptUtil.class);

    private static final String SECRET_KEY = "SPTEKCLOUDPORTAL";
    private static final String SALT = "THEKBCLOUDPORTAL";
    private static final String algorithm = "PBKDF2WithHmacSHA256";
    private static final String transformation = "AES/CBC/PKCS5PADDING";

    public static String encrypt(String password) {

        try {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(SECRET_KEY.getBytes());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey secretKey = factory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] b = cipher.doFinal(password.getBytes("UTF-8"));
            return Base64.encodeBase64String(b);

        } catch (Exception e) {
            logger.warn("[encrypt]", e);
        }
        return password;
    }

    public static String decrypt(String password) {

        try {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(SECRET_KEY.getBytes());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
            KeySpec keySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey secretKey = factory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            Cipher ciper = Cipher.getInstance(transformation);
            ciper.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] b = ciper.doFinal(Base64.decodeBase64(password));
            return new String(b);

        } catch (Exception e) {
            logger.warn("[decrypt]", e);
        }

        return password;
    }

}

