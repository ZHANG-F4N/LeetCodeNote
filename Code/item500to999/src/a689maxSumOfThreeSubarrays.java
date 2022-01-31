import java.util.Arrays;

public class a689maxSumOfThreeSubarrays {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1}, 2)));
    }


    /*
     *
     *
     * */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        long[] pre = new long[nums.length + 1];
        reverse(nums);
        for (int i = 0; i < nums.length; i++) {
            pre[i + 1] += pre[i] + nums[i];
        }

        long[][] dp = new long[nums.length + 1][4];
        for (int i = k; i <= nums.length; i++) {
            for (int j = 1; j < 4; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - k][j - 1] + (pre[i] - pre[i - k]));
            }
        }

        int[] ans = new int[3];

        int j = 3;
        int i = nums.length;
        while (j > 0) {
            if (dp[i - 1][j] > dp[i - k][j - 1] + (pre[i] - pre[i - k])) {
                i--;
            } else {
                ans[--j] = nums.length - i;
                i -= k;
            }
        }
        reverse(ans);
        return ans;
    }

    static void reverse(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int c = nums[l];
            nums[l++] = nums[r];
            nums[r--] = c;
        }
    }

}
