public class a56singleNumber {
    public static void main(String[] args) {
        int[] nums = {9, 1, 7, 9, 7, 9, 7};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {

        int[] bits = new int[32];
        int model = 1;
        for (int i = 0; i < 32; i++) {
            int temp = 0;
            for (int j = 0; j < nums.length; j++) {
                temp += (nums[j] & model) == 0 ? 0 : 1;
            }
            bits[i] = temp;
            model = (model << 1);
        }
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            if (bits[i] % 3 == 1) {
                ans += 1;
            } else {
                ans += 0;
            }
            ans = (ans << 1);
        }
        return ans;
    }
}
