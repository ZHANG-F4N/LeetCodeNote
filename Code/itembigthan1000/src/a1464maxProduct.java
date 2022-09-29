public class a1464maxProduct {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{3, 4, 5, 2}));
    }

    public static int maxProduct(int[] nums) {
        int v1 = Math.max(nums[0], nums[1]), v2 = Math.min(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] >= v1) {
                v2 = v1;
                v1 = nums[i];
            } else if (nums[i] >= v2) {
                v2 = nums[i];
            }
        }
        return (v1 - 1) * (v2 - 1);

    }
}
