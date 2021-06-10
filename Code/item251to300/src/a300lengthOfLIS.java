import java.util.Arrays;

public class a300lengthOfLIS {
    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(nums));

    }
//    //动态规划 O(n^2)
//    public static int lengthOfLIS(int[] nums) {
//        int len = nums.length;
//        int[] dp = new int[len];
//        int ans = 1;
//        for (int i = 0; i < len; i++) {
//            dp[i] = 1;
//        }
//        for (int i = 0; i < len; i++) {
//            for (int j = i - 1; j >= 0; j--) {
//                if (nums[j] < nums[i]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            ans = ans < dp[i] ? dp[i] : ans;
//        }
//        return ans;
//    }
    //贪心+二分 O(n*log(n))
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int ans = 1;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = ans < dp[i] ? dp[i] : ans;
        }
        return ans;
    }
}
