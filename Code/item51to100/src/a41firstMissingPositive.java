import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class a41firstMissingPositive {
    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
//        原地hash
//        for (int i = 0; i < nums.length; i++) {
//            if (0 >= nums[i] || nums[i] > nums.length) {
//                nums[i] = nums.length + 1;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (Math.abs(nums[i]) <= nums.length) {
//                if (nums[Math.abs(nums[i]) - 1] > 0) {
//                    nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
//                }
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > 0) {
//                return i + 1;
//            }
//        }
//
//        return nums.length + 1;


//        额外空间
        int[] flag = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length && nums[i] > 0) {
                flag[nums[i]] = 1;
            }
        }
        for (int i = 1; i < flag.length; i++) {
            if (flag[i] ==0) {
                return i;
            }
        }
        return nums.length + 1;
    }
}
