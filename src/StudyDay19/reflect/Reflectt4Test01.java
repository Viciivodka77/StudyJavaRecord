package StudyDay19.reflect;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Reflectt4Test01 {

    public static void main(String[] args) throws ClassNotFoundException {
        array2Reflect();
    }

    public static void arrayReflect() throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数组的类型：");
        String type = input.next();
        System.out.println("请输入数组的个数");
        int num = input.nextInt();
        Class<?> typeClass = Class.forName(type);
        Object o = Array.newInstance(typeClass, num);
        Array.set(o,0,"zs");
        Array.set(o,1,"ls");
        Array.set(o,2,"ww");
        System.out.println(Array.get(o,0));
        System.out.println(Array.get(o,1));
        System.out.println(Array.get(o,2));
    }

    public static void array2Reflect() throws ClassNotFoundException {
        Class c = Integer.class;
        //数组的长度
        int[] dim = {3,3};
        Object arr = Array.newInstance(c, dim);
        Object arr2 = Array.get(arr, 2);
        Array.set(arr2 , 1,369);
        System.out.println(Array.get(arr2,2));
    }
}
