import java.util.Arrays;

public class a57twoSum {
    public static void main(String[] args) {
        int[] nums = {10, 26, 30, 31, 47, 60};
        int target = 40;

        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{nums[left], nums[right]};
            }
            if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{0, 0};
    }
}
