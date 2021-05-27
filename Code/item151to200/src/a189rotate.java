import java.util.Arrays;

public class a189rotate {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        int[] nums = {-1};
        int k = 3;
        rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        if (k == 0 || nums.length<=1) {
            return;
        }

        int len = nums.length;
        k = k % len;
        reverse(nums,0,len-1);
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);
    }

    public static void reverse(int[] nums, int left, int right) {
        int temp = 0;
        while(left < right){
            temp = nums[right];
            nums[right--] = nums[left];
            nums[left++] = temp;
        }
    }
}
