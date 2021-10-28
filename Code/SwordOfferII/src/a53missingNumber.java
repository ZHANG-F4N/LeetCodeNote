public class a53missingNumber {
    public static void main(String[] args) {
        // 0 1 2 3 4 5 6 7 8
        // 0 1 2 3 4 5 6 7 9
        int[] nums = {0, 1, 3};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 2);
            if (nums[mid] == mid) {
                left = mid + 1;
            }
            if (nums[mid] > mid) {
                right = mid - 1;
            }
        }
        System.out.println(left + " -- " + right);
        return left;

    }
}
