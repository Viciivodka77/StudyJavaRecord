package StudyDay03;

import java.util.*;

/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
示例 1:
输入: [2,2,1]
输出: 1
示例 2:
输入: [4,1,2,1,2]
输出: 4
*/
public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};

//        int answer = circleSolution(nums);//暴力破解
//        int answer = hashSolution(nums);//Hash记录出现次数
//        int answer = setSolution(nums);//Set删除重复数字
        int answer = xorSoluetion(nums);//异或特性
        System.out.println(answer);
    }
    //双重循环 暴力破解法
    private static int circleSolution(int[] nums){
        int record = 0;
        for (int num : nums) {
            int flag = -1;
            for (int i : nums) {
                if (num == i) {
                    flag = -flag;
                }
            }
            if (flag == 1) {
                record = num;
                break;
            }
        }
        return record;
    }
    //Hash记录出现次数
    private static int hashSolution(int[] nums){
        int record = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (Integer num : nums) {
            Integer count = map.get(num);
            count = count == null ? 1 : ++count;
            map.put(num,count);
        }
        for (Integer key : map.keySet()){
            Integer count = map.get(key);
            if (count == 1){
                return key;
            }
        }
        return -1;//can't find
    }
    //set记录删除法
    private static int setSolution(int[] nums){
        int answer = -1;
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            if(!set.contains(num)){
                set.add(num);
            }else {
                set.remove(num);
            }
        }
//        for (Integer integer : set) {
//            answer = integer;
//        }
        Iterator<Integer> i = set.iterator();
        while (i.hasNext()){
            answer = i.next();
        }
        return answer;//can't find
    }
    //异或方法
//    异或运算有以下三个性质。
//    任何数和 00 做异或运算，结果仍然是原来的数，即 a ^ 0=a。
//    任何数和其自身做异或运算，结果是 00，即 a ^ a=0。
//    异或运算满足交换律和结合律，即 a ^ b ^ a=b ^ a ^ a=b ^ (a ^ a)=b ^ 0=b。
    private static int xorSoluetion(int[] nums){
        int answer = nums[0] ;
        for (int i = 1; i < nums.length; i++) {
            answer = answer ^ nums[i];
        }
        return answer;
    }
}
