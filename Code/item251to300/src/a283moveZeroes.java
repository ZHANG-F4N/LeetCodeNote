import java.util.Arrays;

public class a283moveZeroes {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
    }

    public static void moveZeroes(int[] nums) {
        int zeroNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                continue;
            }
            nums[zeroNum++] = nums[i];
        }
        for(int i = zeroNum;i<nums.length;i++){
            nums[i] =0;
        }
        //System.out.println(Arrays.toString(nums));
    }
}
