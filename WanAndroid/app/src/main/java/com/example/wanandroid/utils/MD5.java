package com.example.wanandroid.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hwq
 * @date 2019/12/22.
 * GitHub：
 * Email：
 * Description：
 */
public class MD5 {

    private MD5() {}

    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    /**************************************** Md5加密 ***********************************/

    public static String MD5Encode(String s) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(s.getBytes());
        String md = convertBytesToHex(md5.digest());
        return md;
    }

    public static String convertBytesToHex(byte[] bs) {
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        if (bs == null) {
            return null;
        }
        int len = bs.length;
        char result[] = new char[2 * len];
        int k = 0;

        for (int i = 0; i < len; i++) {
            byte b = bs[i];

            result[k++] = hexDigits[b >>> 4 & 0xf];
            result[k++] = hexDigits[b & 0xf];
        }
        return new String(result);
    }

    // MD516位小写
    public static String Md5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();

//			result = buf.toString().substring(8, 24);
            // 16位的加密
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
