import java.util.ArrayList;
import java.util.List;

public class a46permute {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permute(nums);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List tempList = new ArrayList<Integer>();
        List ansList = new ArrayList<List<Integer>>();
        backtrack();
    }

    public static void backtrack(List<List<Integer>> ansList, List<Integer> tempList, int[] nums, int len) {
        if (len == nums.length) {
            ansList.add(tempList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {

        }
        if (index < nums.length) {
            tempList.add(nums[index++]);
            backtrack(ansList, tempList, nums, len++, index);
        }


    }
}
