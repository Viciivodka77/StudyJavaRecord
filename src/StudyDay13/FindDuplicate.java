package StudyDay13;
/*
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
示例 1:
输入: [1,3,4,2,2]
输出: 2
示例 2:
输入: [3,1,3,4,2]
输出: 3
说明：
不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。
 */


//solution.No2 快慢指针（待解决）


//solution.No1 二分法
public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len -1 ;
        while (left < right){
            // 在Java里可以这么用，当left + right 溢出的时候，无符号右移保证结果正确
            int mid = (left + right) >>>1;
            int count = 0 ;
            for (int num : nums) {
                if (num <= mid){
                    count += 1;
                }
            }
            // 根据抽屉原理
            if(count > mid){
                //重复元素在[left,mid]
                right = mid;
            }else {
                //反之,则重复元素在[mid + 1 ,right]
                left = mid +1;
            }
        }
        //最后结果区间则在重复结果中[重复元素,重复元素]
        return right;
    }
}
