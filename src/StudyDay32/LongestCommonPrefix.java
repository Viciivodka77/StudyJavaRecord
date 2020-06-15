package StudyDay32;

import java.util.LinkedList;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";
        String res = strs[0];
        for (int i = 1; i < len; i++) {
            int j = 0;
            for (;j<res.length() && j<strs[i].length();j++){
                if (res.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            res = strs[i].substring(0,j);
            if (res == "") return res;
        }
        return res;
    }

    public String longestCommonPrefix1(String[] strs){
        if (strs.length==0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0){
                res = res.substring(0,res.length()-1);
                if (res.isEmpty()) return "";
            }
        }
        return res;
    }
}
