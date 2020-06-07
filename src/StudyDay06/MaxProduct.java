package StudyDay06;

public class MaxProduct {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }

    private static int maxProduct(int[] nums){
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0 ){
                dp[i][0] = Math.min(nums[i] , nums[i] * dp[i-1][0]);
                dp[i][1] = Math.max(nums[i] , nums[i] * dp[i-1][1]);
            }else {
                dp[i][0] = Math.min(nums[i] , nums[i] * dp[i-1][1]);
                dp[i][1] = Math.max(nums[i] , nums[i] * dp[i-1][0]);
            }
        }
        int res = dp[0][1];
        for (int i = 0; i < len; i++) {
            res = Math.max(res,dp[i][1]);
        }
        return res;
    }
}
