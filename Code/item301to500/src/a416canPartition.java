import java.util.Arrays;

public class a416canPartition {
    public static void main(String[] args) {
        int[] nums = {2,2,1,1};
        System.out.println(canPartition(nums));
    }

    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum += nums[i];
        }
        int rightSum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (rightSum == leftSum) {
                return true;
            }
            leftSum -= nums[i];
            rightSum += nums[i];
        }
        return false;


    }
}
