import java.util.ArrayList;
import java.util.List;

public class a79subsets {
    public static void main(String[] args) {
        subsets(new int[]{1, 2, 3});
        subsets(new int[]{0});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback2(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    public static void traceback(List<List<Integer>> ans, List<Integer> list, int[] nums,
                                 int idx) {
        ans.add(new ArrayList<>(list));
        for (int i = idx; i < nums.length; i++) {
            list.add(nums[i]);
            traceback(ans, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void traceback2(List<List<Integer>> ans, List<Integer> list, int[] nums,
                                  int idx) {
        if (idx >= nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[idx]);
        traceback(ans, list, nums, idx + 1);
        list.remove(list.size() - 1);
        traceback(ans, list, nums, idx + 1);
    }


}
