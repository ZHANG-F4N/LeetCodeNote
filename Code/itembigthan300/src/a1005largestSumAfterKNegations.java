import java.util.Arrays;

public class a1005largestSumAfterKNegations {
    public static void main(String[] args) {
        System.out.println(largestSumAfterKNegations(new int[]{-4, -2, -3}, 4));
        System.out.println(largestSumAfterKNegations(new int[]{4, 2, 3}, 1));
        System.out.println(largestSumAfterKNegations(new int[]{3, -1, 0, 2}, 3));
        System.out.println(largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int idx = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.abs(nums[i]) < min ? Math.abs(nums[i]) : min;
            if (nums[i] >= 0) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            idx = nums.length;
        }

        int ans = 0;
        if (idx >= k) {
            for (int i = 0; i < k; i++) {
                ans += -nums[i];
            }
            for (int i = k; i < nums.length; i++) {
                ans += nums[i];
            }
            return ans;
        } else {
            for (int i = 0; i < idx; i++) {
                ans += -nums[i];
            }
            for (int i = idx; i < nums.length; i++) {
                ans += nums[i];
            }
            if ((k - idx) % 2 == 0) {
                return ans;
            } else {
                return ans - min * 2;
            }
        }
    }
}
