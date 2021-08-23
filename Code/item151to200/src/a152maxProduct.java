public class a152maxProduct {
    public static void main(String[] args) {
//        int[] nums = {-3, -4, -2};
        int[] nums = {2, -1, 1, 1};
        System.out.println(maxProduct(nums));

    }

    public static int maxProduct(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int maxTemp = nums[0];
        int minTemp = nums[0];
        // ans的默认值应为第一个值，相当于已经处理过了。
        // 因为存在负值，所以不能默认为0。
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int minT = minTemp;
            int maxT = maxTemp;
            //状态转移方程分正负
            maxTemp = Math.max(Math.max(nums[i], nums[i] * minT), maxT * nums[i]);
            minTemp = Math.min(Math.min(nums[i], nums[i] * maxT), minT * nums[i]);
            ans = Math.max(maxTemp, ans);
        }

        return ans;
    }
}
