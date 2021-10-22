import java.util.ArrayDeque;

public class LeetCode1017a3 {
    public static void main(String[] args) {
        System.out.println(countMaxOrSubsets(new int[]{3, 1}));
    }

    private static int ans;

    public static int countMaxOrSubsets(int[] nums) {
        int max = 0;
        ans = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max | nums[i];
        }
        backtrace(max, nums, 0, 0);
        return ans;
    }

    public static void backtrace(int max, int[] nums, int sum, int i) {
        if (i == nums.length) {
            if (sum == max) {
                ans++;
            }
            return;
        }

        int temp = nums[i];
        backtrace(max, nums, sum | temp, i + 1);
        backtrace(max, nums, sum, i + 1);

        return;
    }
}
