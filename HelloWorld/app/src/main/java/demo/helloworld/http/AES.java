package demo.helloworld.http;

import android.annotation.SuppressLint;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * ********************************************************
 * AES加密
 * ********************************************************
 * Created by wangdong on 16/8/9.
 */
@SuppressLint("TrulyRandom")
public class AES {
    //密钥
    public final static String secretKey = "az9cx8L6Q2vkHaiAcXeIQsI6VpUS3Rz1";
    //
    private final static String HEX = "0123456789ABCDEF";
    //android 4.2
    private final static int JELLY_BEAN_4_2 = 17;

    /**
     * AES加密
     *
     * @param plainText 待加密文本
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText) throws Exception {
        return encrypt(secretKey, plainText);
    }

    /**
     * 加密
     *
     * @param secretKey 密钥
     * @param plainText 待加密文本
     * @return
     * @throws Exception
     */
    public static String encrypt(String secretKey, String plainText) throws Exception {
        byte[] rawKey = getRawKey(secretKey.getBytes());
        byte[] result = encrypt(rawKey, plainText.getBytes());
        return toHex(result);
    }

    /**
     * AES解密
     *
     * @param encryptText 待揭秘文本
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptText) throws Exception {
        return decrypt(secretKey, encryptText);
    }

    /**
     * 解密
     *
     * @param secretKey   密钥
     * @param encryptText 待揭秘文本
     * @return
     * @throws Exception
     */
    public static String decrypt(String secretKey, String encryptText) throws Exception {
        byte[] rawKey = getRawKey(secretKey.getBytes());
        byte[] enc = toByte(encryptText);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }

    /**
     * 获取256位的加密密钥
     *
     * @param seed
     * @return
     * @throws Exception
     */
    @SuppressLint("TrulyRandom")
    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = null;
        // 在4.2以上版本中，SecureRandom获取方式发生了改变
        if (android.os.Build.VERSION.SDK_INT >= JELLY_BEAN_4_2) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(seed);
        // 256 bits or 128 bits,192bits
        kgen.init(256, sr);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    /**
     * 真正的加密过程
     *
     * @param key
     * @param src
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src);
        return encrypted;
    }

    /**
     * 真正的解密过程
     *
     * @param key
     * @param encrypted
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] key, byte[] encrypted)
            throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
