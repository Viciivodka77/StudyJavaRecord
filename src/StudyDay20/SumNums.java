package StudyDay20;

/*
面试题64. 求1+2+…+n
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class SumNums {
    public static void main(String[] args) {
        int i = sumNums(5);
        System.out.println(i);
        int j = sumNums1(5);
        System.out.println(j);
    }
    //普通递归转换思路
    public static int sumNums(int n) {
        if (n == 0) return 0;
        return n + sumNums(n-1) ;
    }
    //短路特性
    public static int sumNums1(int n){
        int res = 0;
        Boolean x = n > 0 && (res = n+sumNums1(n-1)) > 0 ;
        return res;
    }
}
