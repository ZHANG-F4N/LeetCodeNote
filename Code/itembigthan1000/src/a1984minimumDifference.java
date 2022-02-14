import java.util.Arrays;

public class a1984minimumDifference {

    public static void main(String[] args) {
        System.out.println(minimumDifference(new int[]{87063, 61094, 44530, 21297, 95857, 93551, 9918}, 6));
        System.out.println(minimumDifference(new int[]{9, 4, 1, 7}, 2));
    }

    public static int minimumDifference(int[] nums, int k) {
        if (k == 1) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        if (k == n) {
            return nums[n - 1] - nums[0];
        }
//        int Max = nums[0];
//        int Maxi = 0;
//        int Min = nums[0];
//        int Mini = 0;
//        for (int i = 0; i < k; i++) {
//            if (nums[i] > Max) {
//                Max = nums[i];
//                Maxi = i;
//            }
//            if (nums[i] < Min) {
//                Min = nums[i];
//                Mini = i;
//            }
//        }
//        for (int i = k; i < n; i++) {
//            if (nums[i] < Max && nums[i] > Min) {
//                int sm = nums[i] - Min;
//                int bg = Max - nums[i];
//                if (sm >= bg) {
//
//                } else {
//
//                }
//            }
//        }
//
//        for (int i = n - 1; i > 0; i--) {
//            nums[i] = nums[i] - nums[i - 1];
//        }
//        nums[0] = 0;
//        Arrays.sort(nums);
//        for (int i = 1; i < n; i++) {
//            nums[i] = nums[i - 1] + nums[i];
//        }
        int ans = Integer.MAX_VALUE;
        for (int i = k - 1; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);
        }

        return ans;


    }
}
