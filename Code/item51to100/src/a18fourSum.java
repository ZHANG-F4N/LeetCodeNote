import java.util.Arrays;
import java.util.List;

public class a18fourSum {
    public static void main(String[] args) {
        fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {


        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = target - nums[i];
        }
        Arrays.sort(nums);

        



    }
}
