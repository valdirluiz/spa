package br.ufsc.ine.aps.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Valdir Luiz on 02/07/2016.
 */
public class Md5Utils {

    public static String convertStringToMd5(String valor) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("MD5");

            byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

            StringBuffer sb = new StringBuffer();
            for (byte b : valorMD5){
                sb.append(Integer.toHexString((b & 0xFF) |
                        0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static void main(String[] args) {
        System.out.printf(convertStringToMd5("123"));
    }


}
