public class a2016maximumDifference {
    public static void main(String[] args) {
        System.out.println(maximumDifference(new int[]{1, 5, 2, 10}));
    }

    public static int maximumDifference(int[] nums) {
        int ans = -1;
        int n = nums.length;
        for (int i = 0, min = nums[0]; i < n; i++) {
            if (nums[i] > min) ans = Math.max(ans, nums[i] - min);
            min = Math.min(min, nums[i]);
        }
        return ans;
    }
}
