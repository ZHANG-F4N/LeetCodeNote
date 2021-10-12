import java.util.Arrays;

public class LeetCode0829a1 {
    public static void main(String[] args) {
        int[] nums = {9, 4, 1, 7};
        int k = 2;
        System.out.println(minimumDifference(nums, k));
    }

    public static int minimumDifference(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0;
        int right = k - 1;
        int min = Integer.MAX_VALUE;
        while (right < nums.length) {
            if (min > Math.abs(nums[right] - nums[left])) {
                min = Math.abs(nums[right] - nums[left]);
            }
            right++;
            left++;
        }
        return min;
    }
}
