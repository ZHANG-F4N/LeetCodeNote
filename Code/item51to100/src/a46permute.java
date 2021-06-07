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
        int[] flag = new int[nums.length];
        backtrack(ansList, flag, tempList, nums, 0);
        return ansList;
    }

    public static void backtrack(List<List<Integer>> ansList, int[] flag, List<Integer> tempList, int[] nums, int len) {
        if (len == nums.length) {
            ansList.add(tempList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i] == 0) {
                tempList.add(nums[i]);
                flag[i] = 1;
                backtrack(ansList, flag, new ArrayList<>(tempList), nums, len + 1);
                flag[i] = 0;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
