package StudyDay39;


/*
面试题 16.18. 模式匹配
你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。
例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"）
该字符串也匹配像"a"、"ab"和"b"这样的模式。
但需注意"a"和"b"不能同时表示相同的字符串。
编写一个方法判断value字符串是否匹配pattern字符串。

示例 1：
输入： pattern = "abba", value = "dogcatcatdog"
输出： true

示例 2：
输入： pattern = "abba", value = "dogcatcatfish"
输出： false

示例 3：
输入： pattern = "aaaa", value = "dogcatcatdog"
输出： false

https://leetcode-cn.com/problems/pattern-matching-lcci/

 */

public class PatternMatching {
    public boolean patternMatching(String pattern, String value) {
        //字符长度为0
        if (value.length() == 0) {
            //匹配串长度不为 0
            if (pattern.length() != 0) {
                for (int i = 1; i < pattern.length(); i++) {
                    //判断是否匹配
                    if (pattern.charAt(i - 1) != pattern.charAt(i)) {
                        return false;
                    }
                }
            }
            return true;
        }
        if (pattern.length() == 0) return false;
        if (pattern.length() == 1) return true;

        char a =pattern.charAt(0);
        //计算出pattern中a和b的个数
        int countA = 0,countB = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (a == pattern.charAt(i)){
                countA++;
            }else{
                countB++;
            }
        }
        int lenP = pattern.length(),lenV = value.length();
        //pattern中只有a，没有b   ->    countA * lenA 应该等于 lenV
        if (countB == 0){
            if (lenV % countA != 0) return false;
            int lenA = lenV / countA;
            String valueA = value.substring(0,lenA);
            for (int i = valueA.length(); i < lenV; i += valueA.length()) {
                if (!value.substring(i, i + lenA).equals(valueA)) return false;
            }
            return true;
        }
        //countA * lenA  + countB * lenB = lenV
        for (int lenA = 0; lenA * countA <= lenV ; lenA++) {
            int temp = lenV - countA *lenA;
            //如果当前lenA的假设无法满足 则跳过当前假设
            if (temp % countB != 0 ) continue;
            int lenB = temp / countB,ci = 0;
            boolean isMatch = true;
            String valueA = "",valueB = "";
            for (char c : pattern.toCharArray()) {
                if (c == a) {
                    String cur_a = value.substring(ci,ci += lenA);
                    if (valueA.length() == 0 ){
                        valueA = cur_a;
                    }else if (!valueA.equals(cur_a)){
                        isMatch = false;
                        break;
                    }
                }else {
                    String cur_b = value.substring(ci , ci += lenB);
                    if (valueB.length() == 0){
                        valueB = cur_b;
                    }else if (!valueB.equals(cur_b)){
                        isMatch = false;
                        break;
                    }
                }
            }
            if (isMatch && !valueA.equals(valueB)){
                return true;
            }
        }
        return false;
    }
}