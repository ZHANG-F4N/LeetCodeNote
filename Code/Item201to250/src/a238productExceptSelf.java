import java.lang.reflect.Array;
import java.util.Arrays;

public class a238productExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    //    // n^2
//    public static int[] productExceptSelf(int[] nums) {
//        int ans[] = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            int temp = 1;
//            for (int j = 0; j < nums.length; j++) {
//                if (i == j) {
//                    continue;
//                }
//                temp *= nums[j];
//            }
//            ans[i] = temp;
//        }
//        return ans;
//    }
    public static int[] productExceptSelf(int[] nums) {
        int L_multi[] = new int[nums.length];
        int R_multi[] = new int[nums.length];
        L_multi[0] = 1;
        R_multi[nums.length - 1] = 1;


        for (int i = 1; i < nums.length; i++) {
            L_multi[i] = L_multi[i - 1] * nums[i - 1];
            R_multi[nums.length - i - 1] = R_multi[nums.length - i] * nums[nums.length - i];
        }
        int ans[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = L_multi[i] * R_multi[i];
        }
        return ans;
    }
}
