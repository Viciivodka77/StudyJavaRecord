package StudyDay16;

public class Rob {

    public static void main(String[] args) {
        int[] nums = {3,5,5,4};
        System.out.println(rob1(nums));
    }

    //dp
    private static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < len + 1; i++) {
            dp[i] = Math.max(dp[i-1] , dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }

    //递归
    private static int rob1(int[] nums){

        return robTo(nums,nums.length-1);
    }
    private static int robTo(int[] nums, int index){
        if (index == 0){
            return nums[0];
        }else if (index < 0){
            return 0;
        }
        int sum1 = robTo(nums,index-2) + nums[index];
        int sum2 = robTo(nums,index-1);
        return Math.max(sum1,sum2);
    }


}
