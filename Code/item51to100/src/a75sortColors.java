import java.util.Arrays;

public class a75sortColors {
    public static void main(String[] args) {
        int[] nums = {2, 0,2,1,0,2,1,0,2,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void sortColors(int[] nums) {
//        int point_0 = 0;
//        int point_2 = nums.length - 1;
//        for (int i = 0; i <= point_2; i++) {
//            while (i < point_2 && nums[i] == 2) {
//                int temp = nums[point_2];
//                nums[point_2] = nums[i];
//                nums[i] = temp;
//                point_2--;
//            }
//
//            if (nums[i] == 0) {
//                int temp = nums[point_0];
//                nums[point_0] = nums[i];
//                nums[i] = temp;
//                point_0++;
//            }
//        }

        int point_0 = 0;
        int point_2 = nums.length - 1;
        while (point_0 <= point_2) {
            if (nums[point_2] == 2) {
                point_2--;
                continue;
            }
            if (nums[point_0] == 0) {
                point_0++;
                continue;
            }
            if (nums[point_0] == 2) {
                int temp = nums[point_0];
                nums[point_0] = nums[point_2];
                nums[point_2] = temp;
                point_2--;
                continue;
            }
            if (nums[point_2] == 0) {
                int temp = nums[point_0];
                nums[point_0] = nums[point_2];
                nums[point_2] = temp;
                point_0++;
                continue;
            }
            if (nums[point_0] == 1 && nums[point_2] == 0) {
                int temp = nums[point_0];
                nums[point_0] = nums[point_2];
                nums[point_2] = temp;
                point_2--;
                continue;
            }
            point_0++;
            point_2--;
        }
    }
}
