import java.util.Arrays;

public class a21exchange {
    public static void main(String[] args) {
        int[] nums = {1, 3, 7, 7, 2, 4};

        System.out.println(Arrays.toString(exchange(nums)));
    }

    public static int[] exchange(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            //判断奇偶

//          奇数  (nums[left] & 1) == 1
//          偶数  (nums[left] & 1) == 0
            while (left < right && (nums[left] & 1) == 1) {
                left++;
            }
            while (left < right && (nums[right] & 1) == 0) {
                right--;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }
}
