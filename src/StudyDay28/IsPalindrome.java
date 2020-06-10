package StudyDay28;

public class IsPalindrome {
    public static boolean isPalindrome(int x) {
//        if (x < 0){
//            return false;
//        }
//        String s = String.valueOf(x);
//        int len = s.length();
//        for (int i =0,j = len -1;i < len && j >= 0 ;--j, ++i) {
//            if (s.charAt(i) != s.charAt(j)) {
//                return false;
//            }
//        }
//        return true;
        //解法二
//        String str = new StringBuffer(x + "").reverse().toString();
//        return (x+"").equals(str);
        //解法三
        //边界判断
        if (x < 0) {
            return false;
        }
        int div = 1;
        //
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(656));
    }
}
