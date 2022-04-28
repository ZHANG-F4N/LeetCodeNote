public class a396maxRotateFunction {
    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[]{4, 3, 2, 6}));
    }

    public static int maxRotateFunction(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int n = nums.length;

        int val = 0;
        for (int i = 0; i < n; i++) {
            val += nums[i] * i;
        }
        int ans = val;
        for (int i = 0; i < n-1; i++) {
            val = val - (sum - nums[i]) + (n - 1) * nums[i];
            ans = Math.max(ans, val);
        }
        return ans;

    }
}
