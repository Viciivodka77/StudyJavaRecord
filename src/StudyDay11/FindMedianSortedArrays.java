package StudyDay11;
/*
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
你可以假设 nums1 和 nums2 不会同时为空。

示例 1:
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0
示例 2:
nums1 = [1, 2]
nums2 = [3, 4]
则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mLen = nums1.length;
        int nLen = nums2.length;
        //创建新数组 长度为nums1的长度 + nums2的长度
        int[] nums = new int[mLen + nLen];
        //如果某个数组为空，则中位数为另外一个数组的中位数（分奇偶）
        if (mLen == 0){
            //如果长度是偶数，则
            if (nLen % 2 == 0){
                return (nums2[nLen/2 -1] + nums2[nLen/2])/2.0;
            }else {
                //如果长度是奇数
                return nums2[nLen/2];
            }
        }
        //同理
        if (nLen == 0){
            if (mLen % 2 == 0){
                return (nums1[mLen/2 -1] + nums1[mLen/2])/2.0;
            }else {
                return nums1[mLen/2];
            }
        }

        int count = 0 ;
        int i = 0, j = 0;
        //循环存入数据到新数组中
        while(count != (mLen + nLen)){
            //如果数组nums1数据放完了，则直接放nums2中数据即可
            if (i == mLen){
                while(j != nLen){
                    nums[count ++] = nums2[j++];
                }
                break;
            }
            //反之同理
            if (j == nLen){
                while(i != mLen){
                    nums[count ++] = nums1[i++];
                }
                break;
            }
            //分情况按大小顺序把nums1和nums2的数据放入nums中
            if (nums1[i] < nums2[j]){
                nums[count++] = nums1[i++];
            }else {
                nums[count++] = nums2[j++];
            }
        }
        //根据新数组的长度 分奇偶 返回中位数
        if (count % 2 == 0){
            return (nums[count/2 - 1] + nums[count/2]) / 2.0;
        }else {
            return nums[count/2];
        }
    }
}
