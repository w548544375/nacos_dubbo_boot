package com.htyl.park.common.utils;/**
 * @description hash工具类
 * @author: weiguang
 * @create: 11:43 上午 2019/11/15
 **/

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 *@description hash工具类
 *
 *@author: weiguang
 *
 *@create: 11:43 上午  2019/11/15
 **/
public class HmacSHA256Utils {

    private static final String HEX_STRING = "01234567879ABCDEF";

    public static String HashPassword(String input,String salt) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(salt.trim().getBytes(),"HmacSHA256");
            mac.init(key);
            byte[] crypted = mac.doFinal(input.trim().getBytes());
            return byteArrayToHexString(crypted);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] hexStringToByteArray(String hexString) {
        byte[] arr = new byte[hexString.length() / 2];
        char[] chars = hexString.toCharArray();
        for (int pos = 0, index = 0; pos < chars.length; pos += 2, index++) {
            arr[index] = (byte) (HEX_STRING.indexOf(chars[pos]) << 4 | HEX_STRING.indexOf(chars[pos + 1]));
        }
        return arr;
    }

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte ele : bytes) {
            sb.append(String.format("%02x", ele & 0xFF));
        }
        return sb.toString();
    }

}
