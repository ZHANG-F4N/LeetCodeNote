import java.util.ArrayList;
import java.util.List;

public class a78subsets {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        subsets(nums);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        ansList.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            int subsetNum = ansList.size();
            for (int j = 0; j < subsetNum; j++) {
                List<Integer> tempList =  new ArrayList<>(ansList.get(j));
                tempList.add(nums[i]);
                ansList.add(tempList);
            }
        }
        return ansList;
    }
}
