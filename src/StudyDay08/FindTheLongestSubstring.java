package StudyDay08;

import java.util.Arrays;

public class FindTheLongestSubstring {

    public static void main(String[] args) {
        String s = "eleetminicoworoep";
        System.out.println(findTheLongestSubstring(s));
    }

    public static int findTheLongestSubstring(String s) {
        int len = s.length();
        int[] arr = new int[1 << 5];
        Arrays.fill(arr,-1);
        System.out.println(Arrays.toString(arr));
        int status = 0,res = 0;
        arr[0] = 0;
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == 'a'){
                status ^= (1 << 0);
                System.out.println(status);
            }else if (ch == 'e'){
                status ^= (1 << 1);
                System.out.println(status);
            }else if (ch == 'i'){
                status ^= (1 << 2);
                System.out.println(status);
            }else if (ch == 'o'){
                status ^= (1 << 3);
                System.out.println(status);
            }else if (ch == 'u'){
                status ^= (1 << 4);
                System.out.println(status);
            }
            if (arr[status] >= 0){
                res = Math.max(res , i+1-arr[status]);
                System.out.println(res);
            }else {
                arr[status] = i + 1 ;
                System.out.println(Arrays.toString(arr));
            }
        }
        return res;
    }
}
