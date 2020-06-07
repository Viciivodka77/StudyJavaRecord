package StudyDay24.Encode;

import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SecurityUtil {

    //异或  实现加密与解密:传入String("abc") -> String("xyz")
    public static String xorEncode(String input){
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ 520);
        }
        return new String(chars);
    }

    /*
        MD5/SHA256 字节串 -> 十六进制串
        不可逆
     */
    //MD5加密
    public static String md5Encode(String input){
        return DigestUtils.md5Hex(input);
    }

    public static String sha256Encode(String input){
        return DigestUtils.sha256Hex(input);
    }
    /*
        MD5:不可逆，速度较快
        SHA256:不可逆，安全性较高
     */

    //Base64加密
    public static String base64Encode(byte[] input){
        String res = null;
        try {
            Class<?> aClass = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method encode = aClass.getMethod("encode", byte[].class);
//            Object o = aClass.newInstance();
            res =(String) encode.invoke(null, input);//静态方法不需要调用对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    //Base64解密
    public static byte[] base64Decode(String input){
        byte[] res = null;
        try {
            Class<?> aClass = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method decode = aClass.getMethod("decode", String.class);
            res = (byte[]) decode.invoke(null,input);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "123456";
        System.out.println(str);
        str = xorEncode(str);
        System.out.println("加密:"+str);
        str = xorEncode(str);
        System.out.println("解密:"+str);

        //md5
        String s = md5Encode(str);
        System.out.println(s);

        String s1 = sha256Encode(str);
        System.out.println(s1);

        //base64
        String s2 = base64Encode(str.getBytes());
        System.out.println(s2);
        byte[] bytes = base64Decode(s2);
        System.out.println(new String(bytes));
    }
}
