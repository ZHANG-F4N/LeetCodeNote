public class a90rob {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }

    public static int rob(int[] nums) {
        // 2  3  3
        // 1  2  4
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];

        // rob 0
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int j = 2; j < n - 1; j++) {
            dp[j] = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
        }
        // start i
        int ans = dp[n - 2];
        // rob 1
        dp[0] = 0;
        dp[1] = nums[1];
        for (int j = 2; j < n; j++) {
            dp[j] = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
        }
        ans = Math.max(ans, dp[n - 1]);
        return ans;
    }
}
