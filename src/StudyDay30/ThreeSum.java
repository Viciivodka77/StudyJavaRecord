package StudyDay30;

import com.sun.xml.internal.ws.message.source.PayloadSourceMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum{
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i-1]) continue;//去重
            int L = i + 1;
            int R = len -1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i] , nums[L] , nums[R]));
                    while ( L < R && nums[L] == nums[L+1]) L++;//去重
                    while ( L < R && nums[R] == nums[R-1]) R--;//去重
                    L++;
                    R--;
                }else if (sum < 0){
                    L++;
                }else {
                    R--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4} ;
        System.out.println(threeSum(nums));
    }
}
