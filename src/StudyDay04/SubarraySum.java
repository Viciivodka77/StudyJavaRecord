package StudyDay04;

import java.util.HashMap;
import java.util.Map;

/*
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
示例 1 :
输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :
数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class SubarraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,3,1,2};
        int k = 3;
//        System.out.println(circleSolution(nums,k));//循环暴力破解
        System.out.println(prefixSolution(nums,k));//前缀和
//        System.out.println(prefixHashSolution(nums,k));//前缀和+哈希表优化
    }
    //循环暴力破解 O(n^2)
    public static int circleSolution(int[] nums,int k){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k){
                    count ++;
                }
            }
        }
        return count;
    }
    //前缀和O(n^2)
    public static int prefixSolution(int[] nums,int k){
        int[] preSum = new int[nums.length+1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < preSum.length; i++) {
            for (int j = i+1; j < preSum.length; j++) {
                if (preSum[j] - preSum[i] == k){
                    count++;
                }
            }
        }
        return count;
    }
    //前缀和 + 哈希表 优化 O(n)
    public static int prefixHashSolution(int[] nums,int k){
        int count = 0;
        int preSum = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();//key为前缀和，value为出现次数
        prefixMap.put(0,1);
        for (int num : nums) {//循环记录每个preSum以及次数
            preSum += num;
            if (prefixMap.containsKey(preSum - k)){
                //如果已经有了preSum-k，说明当前下标，到之前preSum-k的下标，之中含有子集合 = k，则count加上之前出现的个数
                //个数大于1代表之前有多个子集合已经满足，再加上当前的下标的数字，也满足子集合 = k 的条件
                count += prefixMap.get(preSum-k);
            }
            //记录当前preSum，如果此前出现过preSum 用getOrDefault处理
            prefixMap.put(preSum,prefixMap.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}
