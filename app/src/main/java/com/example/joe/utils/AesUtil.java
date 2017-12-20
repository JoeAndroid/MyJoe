package com.example.joe.utils;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


public class AesUtil {

    static String key = "wzyanzhiappdengn";
    static String iv = "wzyanzhiappdengn";

    static String keyh5 = "yanzhih5yanzhih5";
    static String ivh5 = "yanzhih5yanzhih5";

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
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

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
        try {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            List<Byte> bb = new ArrayList<Byte>();
            for (int i = 0; i < original.length; i++) {
                if (original[i] != 0) {
                    bb.add(original[i]);
                }
            }
            Byte[] a = bb.toArray(new Byte[0]);
            byte[] res = new byte[a.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = a[i].byteValue();
            }
            String originalString = new String(res, "UTF-8");
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptH5(String data) throws Exception {
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
            SecretKeySpec keyspec = new SecretKeySpec(keyh5.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(ivh5.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptH5(String data) throws Exception {
        try {
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(keyh5.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(ivh5.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            List<Byte> bb = new ArrayList<Byte>();
            for (int i = 0; i < original.length; i++) {
                if (original[i] != 0) {
                    bb.add(original[i]);
                }
            }
            Byte[] a = bb.toArray(new Byte[0]);
            byte[] res = new byte[a.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = a[i].byteValue();
            }
            String originalString = new String(res, "UTF-8");
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
