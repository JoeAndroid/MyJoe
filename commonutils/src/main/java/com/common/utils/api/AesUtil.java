package com.common.utils.api;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * 接口请求加密 解密工具类
 *
 * @author shenxiaolei
 */
public class AesUtil {
    // 加密和解密密匙
    public static final String key = "wzyanzhiappdengn";

    public static final String iv = "wzyanzhiappdengn";

    public static final String h5Key = "yanzhih5yanzhih5";

    public static final String h5Iv = "yanzhih5yanzhih5";

//    public static void main(String args[]) throws Exception {
//        String encryptResult = encrypt("Test String");
//        System.out.println(encryptResult);
//        String decryptResult = decrypt(encryptResult);
//        System.out.println(decryptResult);
//    }

    public static String encrypt(String key, String iv, String data) throws Exception {
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new BASE64Encoder().encode(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String data) throws Exception {
        try {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
