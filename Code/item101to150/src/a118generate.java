import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a118generate {
    public static void main(String[] args) {
        int []nums = {9,6,4,2,3,5,7,0,1};
        generate(5);
        System.out.println(missingNumber(nums));
    }

    public static List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> ansList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    temp.add(ansList.get(i-1).get(j-1)+ansList.get(i-1).get(j));
                }
            }
            ansList.add(temp);
        }
        return ansList;
    }
    public static int missingNumber(int[] nums) {
        long sum =  (nums.length*(nums.length+1))/2;
        long numsSum = 0;
        for (int i = 0; i < nums.length; i++) {
            numsSum+=nums[i];
        }
        System.out.println(sum+" "+numsSum);
        int ans = (int)(sum - numsSum);
        return ans;
    }
}
