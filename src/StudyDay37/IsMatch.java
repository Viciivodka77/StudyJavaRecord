package StudyDay37;
/*
10. 正则表达式匹配
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
说明:
s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
https://leetcode-cn.com/problems/regular-expression-matching/
 */


public class IsMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int rows = s.length(),cols = p.length();
        boolean[][] dp = new boolean[rows+1][cols+1];
        // 初始化
        dp[0][0] = true;
        for (int j = 1; j <= cols; j++) {
            if (p.charAt(j-1) == '*' && dp[0][j-2]){
                dp[0][j] = true;
            }
        }
        for(int i = 1; i <= rows; i++){
            dp[i][0] = false;
        }
        //dp
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                char curS = s.charAt(i-1);
                char curP = p.charAt(j-1);
                if (curS == curP){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    if (curP == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }else if (curP == '*'){
                        if (j>=2){
                            char LastP = p.charAt(j-2);
                            if (LastP == curS || LastP == '.'){
                                dp[i][j] = dp[i-1][j] || dp[i][j-1];
                            }
                            dp[i][j] = dp[i][j] || dp[i][j-2];
                        }
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[rows][cols];
    }
}
