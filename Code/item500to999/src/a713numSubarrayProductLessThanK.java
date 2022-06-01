public class a713numSubarrayProductLessThanK {
    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 1, 1}, 2));
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0));
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = -1;
        int ans = 0;
        long multi = 1;

        while (++r < n) {
            multi *= nums[r];
            while (multi >= k && l <= r) multi /= nums[l++];
            ans += r - l + 1;
        }

        return ans;
    }
}
