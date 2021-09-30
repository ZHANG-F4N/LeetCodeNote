public class LeetCode0926a1 {
    public static void main(String[] args) {
        int[] nums = {7, 7, 5, 4};
        System.out.println(maximumDifference(nums));
    }

    public static int maximumDifference(int[] nums) {
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j] && nums[j] - nums[i] > max) {
                    max = nums[j] - nums[i];
                }
            }
        }

        return max;
    }
}
