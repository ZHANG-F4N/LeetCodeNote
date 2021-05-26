//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//	        序号         0	1	2	3	4	5	6	7	8
//          值          -2	1	-3	4	-1	2	1	-5	4
//      如果前一个数大于0,将前面的数加上
//          最大和       -2	1	-2	4	4	5	6	6	6

import java.lang.reflect.Array;
import java.util.Arrays;

//          状态转移方程 f(i)=max{f(i−1)+nums[i],nums[i]}
//顺序查找即可,每次只需要比较 MAX{前n-1的最大序列和, (不连续)从N-1最大序列和最后一位到当前值和}
public class maxSubArray_53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray(nums);
    }
    public static int maxSubArray(int[] nums) {
        int MAXArray = nums[0];
        for (int i = 1;i<nums.length;i++){
            if(nums[i-1] > 0){
                nums[i]=nums[i]+nums[i-1];
            }
            if (MAXArray < nums[i]){
                MAXArray = nums[i];
            }
        }
        return MAXArray;
    }
}
