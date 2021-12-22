import java.util.Arrays;

public class a740deleteAndEarn {
    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{1}));
        System.out.println(deleteAndEarn(new int[]{3, 3, 3, 4, 2}));
        System.out.println(deleteAndEarn(new int[]{1, 1, 1, 2, 4, 5, 5, 5, 6}));
    }

    public static int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] val = new int[maxVal + 1];
        for (int i = 0; i < nums.length; i++) {
            val[nums[i]]++;
        }
        int[] dp = new int[val.length];
        int ans = val[1];
        dp[1] = val[1];
        for (int i = 2; i < val.length; i++) {
            dp[i] = Math.max(i * val[i] + dp[i - 2], dp[i - 1]);
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}
