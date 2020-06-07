package StudyDay01;

import java.util.Scanner;

public class Demo01 {
    public static void main(String[] args) {
        System.out.println("============");
        //输入一个三位数，求各个位数之和
        Scanner scanner =new Scanner(System.in);
        Integer a = scanner.nextInt();
        /*
        方法一
        int a1 = a / 100;
        int a2 = a / 10 % 10;
        int a3 = a % 10;
        System.out.println(a1 + a2 + a3);
        */
        // 方法二
        int sum = 0;
        for (int i = 0; i < a.toString().length(); i++) {
            int temp;
            temp = (int) (a / Math.pow(10,i) %10);
            sum += temp;
        }
        System.out.println(sum);
        //交换数字
        System.out.println("=============");
        Scanner scanner1 =new Scanner(System.in);
        int b = scanner1.nextInt();
        int b1 = b / 10 % 10;
        int b2 = b % 10;
        int b3 = b2 * 10 + b1 ;
        System.out.println(b3);
    }
}
