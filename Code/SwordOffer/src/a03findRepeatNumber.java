public class a03findRepeatNumber {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }

    public static int findRepeatNumber(int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[nums[i]]++;
            if (temp[nums[i]] >= 2) {
                return nums[i];
            }
        }
        return 0;
    }
}
