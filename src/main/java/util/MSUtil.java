package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: stone
 * @program: EasyBuy
 * @description:
 * @date: 2021-11-30 14:34:07
 */
public class MSUtil {
    //使用md5加密算法
    public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = msg.getBytes();
            byte[] output = md.digest(input);
            String str = new String(output);
//            String str = Base64.encodeBase64String(output);
            return str;
        } catch (NoSuchAlgorithmException e){
            System.out.println("密码加密失败");
            return "";
        }
    }
}
