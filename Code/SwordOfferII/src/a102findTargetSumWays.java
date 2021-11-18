import java.util.Arrays;

public class a102findTargetSumWays {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 2, 3, 4, 5}, 3));
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1}, 1));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        /*
         * dp[i]    0   1   2   3   4   5   6   7   8   9   10  11  12
         *      1   1   1   0   0   0   0   0   0   0   0
         *      2   1   1   1   1   0   0   0   0   0   0
         *      3   1   1   1   2   1   1   1   0   0   0
         *      4   1   1   1   2   1   2   2
         *      5
         * */
        int sum = Arrays.stream(nums).sum();
        target = sum - target;
        if (target < 0 || target % 2 != 0) {
            return 0;
        }
        target /= 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= 0; j--) {
                if (j - nums[i] >= 0)
                    dp[j] = dp[j - nums[i]] + dp[j];
            }
        }
        return dp[target];
    }
}
