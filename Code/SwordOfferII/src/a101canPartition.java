import java.util.Arrays;

public class a101canPartition {
    public static void main(String[] args) {
        System.out.println(canPartition2(new int[]{1, 2, 5}));
        System.out.println(canPartition2(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition2(new int[]{1, 2, 3, 5}));
    }

    //dp(i, j) 表示能否从前 i 个物品(物品编号为 0 ~ i - 1)中选择若干物品放满容量为 j 的背包。
    public static boolean canPartition(int[] nums) {
        // 从 nums[0:n-1] 中选出一部分和为 sum/2
        /*
         *   0   1   2   3   4   5   6   7   8   9   10  11
         *   1   1   0   0   0   0   0   0   0   0   0   0
         *   5   1   0   0   0   1   1   0   0   0   0   0
         *   11  1   0   0   0   1   1   0   0   0   0   1
         *   5
         * */
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;

        boolean[][] dp = new boolean[nums.length][sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[0][i] = i == nums[0] ? true : false;
        }
        if (dp[0][sum]) {
            return true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] =
                        dp[i - 1][j] || (j - nums[i] > 0 && dp[i - 1][j - nums[i]]) || j == nums[i];
            }
            if (dp[i][sum]) {
                return true;
            }
        }
        return false;
    }


    // 回溯 直接 tmd 超时
    private static boolean ans;

    public static boolean canPartition2(int[] nums) {
        ans = false;
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        traceback(nums, 0, sum);
        return ans;
    }

    public static void traceback(int[] nums, int idx, int tar) {
        if (tar == 0) {
            ans = true;
            return;
        }
        if (ans||idx >= nums.length) {
            return;
        }
        traceback(nums, idx + 1, tar - nums[idx]);
        traceback(nums, idx + 1, tar);
    }
}
