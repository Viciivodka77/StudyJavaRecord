package StudyDay31;

import java.util.Arrays;

public class FindBestValue {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int len = arr.length;
        int[] prefix = new int[len + 1];
        //记录前缀和
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i-1] + arr[i-1];
        }
        //边界为最大值
        int max = arr[len-1];
        int res = 0,diff = target;
        //循环
        for (int i = 1; i <= max; i++) {
            //二分查找出第一个i
            int index = Arrays.binarySearch(arr,i);
            //如果不存在则
            if (index < 0){
                index = -index - 1;
            }
            //当前的和为 这个位置之前的前缀和加上之后个数的i
            int cur = prefix[index] + (len-index) * i;
            //维护res 和 diff
            if (Math.abs(cur - target) < diff){
                res = i;
                diff = Math.abs(cur - target);
            }
        }
        return res;
    }
}
