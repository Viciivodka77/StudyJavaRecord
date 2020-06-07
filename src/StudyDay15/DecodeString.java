package StudyDay15;

/*
    394. 字符串解码
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
    示例:
    s = "3[a]2[bc]", 返回 "aaabcbc".
    s = "3[a2[c]]", 返回 "accaccacc".
    s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

 */


public class DecodeString {
    public String decodeString(String s) {
        return dfs(s,0)[0];
    }

    public String[] dfs(String s, int i){
        StringBuffer res = new StringBuffer();
        int multi = 0;
        //循环遍历字符串所有字符
        while(i < s.length()){
            //判断如果是数字，那么计算倍数，（！注意！ 倍数可能是两位数）
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                //注意对两位数的倍数处理
                multi = multi*10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            }else if (s.charAt(i) == '['){
                //如果是'['则开启一个递归 入口 ，创建一个temp得到'['内的字符串组。
                // 第一位为i的值，第二位为字符串
                // 所以函数体使用String[]作为返回值
                String[] temp = dfs(s,i+1);
                //重新设置 i ，跳过当前已经递归的得到的字符串
                i = Integer.parseInt(temp[0]);
                // 根据倍数 ，循环'['内的字符 ，并且重置倍数为0
                while (multi > 0){
                    res.append(temp[1]);
                    multi--;
                }
            }else if (s.charAt(i) == ']'){
                //递归出口 如果字符为']' 则结束当前递归，返回当前的i值 和 当前res的字符串（即'['和']'内的字母）
                return new String[] { String.valueOf(i) , res.toString()};
            }else {
                //其他情况为字母,循环加入当前递归中的每一个字母,用于倍数的循环
                res.append(String.valueOf(s.charAt(i)));
            }
            //指针移动
            i++;
        }
        //最后返回
        return new String[] {res.toString()};
    }

    public static void main(String[] args) {
        String s = "3[a]";
        DecodeString decodeString = new DecodeString();
        String string = decodeString.decodeString(s);
        System.out.println(string);
    }
}
