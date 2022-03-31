public class a2044countMaxOrSubsets {
    public static void main(String[] args) {
        System.out.println(countMaxOrSubsets(new int[]{3, 1}));
    }

    public static int ans;

    public static int countMaxOrSubsets(int[] nums) {
        ans = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = max | nums[i];
        }
        backtrace(nums, max, 0, 0);
//        backtrace(nums, max, nums[0], 0);
        return ans;
    }


    public static void backtrace(int[] nums, int max, int val, int i) {
        if (i == nums.length) {
            if (val == max) ans++;
            return;
        }
        backtrace(nums, max, val | nums[i], i + 1);
        backtrace(nums, max, val, i + 1);
    }
}
