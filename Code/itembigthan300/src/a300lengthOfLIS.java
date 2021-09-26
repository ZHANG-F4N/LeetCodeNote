import java.util.Arrays;

public class a300lengthOfLIS {
    public static void main(String[] args) {
//        int[] nums = {10, 9, 2, 5, 3, 7, 21, 18, 6};
        int[] nums = {2,2,2,2};
        System.out.println(lengthOfLISbyBinary(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = max > dp[j] ? max : dp[j];
                }
            }
            dp[i] = max + 1;
            ans = ans > dp[i] ? ans : dp[i];
        }
        return ans;
    }

    // dp+二分
    // 我们考虑维护一个列表 tails，
    // 其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值。
    // nums     10  9   2   5   3   7  21  18
    // index    0   1   2   3   4   5   6
    // tails    10  5   7   21
    //          9   3       18
    //          2
    // tails 的长度就是答案
    public static int lengthOfLISbyBinary(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
}
