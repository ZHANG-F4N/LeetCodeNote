import java.util.Arrays;

public class removeDuplicates_80 {
    public static void main(String[] args) {
//        int[] nums = {1,1,1,2,2,3};
        int[] nums = {0,0,1,1,1,1,2,3,3};

        removeDuplicates(nums);
    }
//     i voidIndex		1	1	1	2	2	3
//     0	0	    	1
//     1	1	    	1	1
//     2	2	    	1	1
//     3	2	    	1	1	2
//     4	3	    	1	1	2	2
//     5	4	    	1	1	2	2	3

    public static int removeDuplicates(int[] nums) {
        int voidIndex = 1;
        if(nums.length <=2){
            return 1;
        }
        int  flag =1 ;//相等个数
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[voidIndex-1] ){
                flag = 1;
                nums[voidIndex++] = nums[i];
            }else {
                flag++;
                if (flag>2){
                    continue;
                }
                else {
                    nums[voidIndex++] = nums[i];
                }
            }
        }
        //System.out.println(Arrays.toString(nums)+voidIndex);
        return  voidIndex;
    }
}
