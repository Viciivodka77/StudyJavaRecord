package StudyDay22;

/*
238. 除自身以外数组的乘积
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

示例:
输入: [1,2,3,4]
输出: [24,12,8,6]
 */


public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0){
            return new int[]{0};
        }
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            int fixMulti = 1;
            int subMulti = 1;
            if (i == 0){
                //只会有后缀积
                int j = i;
                while( ++j < len){
                    subMulti = subMulti * nums[j];
                }
            }else if (i == len -1){
                //只有前缀和
                int j = i;
                while (--j >= 0){
                    fixMulti = fixMulti * nums[j];
                }
            }else {
                //有前缀和后缀
                int j = i;
                while( ++j < len){
                    subMulti = subMulti * nums[j];
                }
                j = i;
                while (--j >= 0){
                    fixMulti = fixMulti * nums[j];
                }
            }
            res[i] = fixMulti * subMulti;
        }
        return res;
    }

    //优化
    public int[] productExceptSelf1(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < res.length; i++) {
            res[i] = k;
            k = k * nums[i];// 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;//重置k 记录右侧元素乘积
        for (int i = res.length - 1; i >= 0 ; i--) {
            res[i] *= k; //原本res中已经存有左边元素乘积 再乘上右侧
            k = k * nums[i];// 此时数组存储的是除去当前元素右边的元素乘积
        }
        return res;
    }
}
