package StudyDay07;

public class ValidPalindrome {


    public static void main(String[] args) {
        String s ="deeeeee";
        System.out.println(validPalindrome(s));
    }

    //双指针
//    public boolean validPalindrome(String s) {
//        //双指针 low指向第一个字符，high指向最后一个字符
//        int low = 0, high = s.length() - 1;
//        //循环只要low和high没有相遇就循环
//        while(low < high){
//            //拿道分别指向的字符
//            char c1 = s.charAt(low) , c2 = s.charAt(high);
//            //如果字符相等则向前走一步（low ++； high他--；）
//            if (c1 == c2){
//                low ++;
//                high --;
//            } else {
//                //如果不相等，则有两种情况 删除low所在的字符，或者删除high所在的字符
//                //设置两种情况的判断
//                boolean flag1 = true,flag2 = true;
//                //第一种情况：删除high所在的字符 high - 1
//                //再重复向前循环 如果有不相等字符 则不是回文串，设置判断为false，终止循环
//                for (int i = low,j = high -1; i < j; i++,j--) {
//                    char c3 = s.charAt(i) , c4 = s.charAt(j);
//                    if (c3 != c4){
//                        flag1 = false;
//                        break;
//                    }
//                }
//                //第一种情况：删除low所在的字符  low + 1，同上
//                for (int i = low + 1,j = high; i < j; i++,j--) {
//                    char c3 = s.charAt(i) , c4 = s.charAt(j);
//                    if (c3 != c4){
//                        flag2 = false;
//                        break;
//                    }
//                }
//                //返回 或 （如果有一个满足即可）
//                return flag1 || flag2;
//            }
//        }
//        //循环无不相等字符 最终返回true
//        return true;
//    }
    //双指针 ＋ 递归回溯
    private static int delCount = 0;
    public static boolean validPalindrome(String s) {
        int low = 0, high = s.length() -1;
        while (low < high){
            if (s.charAt(low) != s.charAt(high)){
                delCount++;
                if (delCount > 1){
                    return false;
                }else {
                    //注意substring方法取值范围为[a,b) a可取，b不可取
                    return validPalindrome(s.substring(low+1,high+1)) || validPalindrome(s.substring(low,high));
                }
            }
            low ++;
            high --;
        }
        return true;
    }
}
