import java.util.ArrayList;
import java.util.List;

public class a83permute {
    public static void main(String[] args) {
        permute(new int[]{0, -1, 1});
        permute(new int[]{1, 2, 3});

    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        traceback(ans, new ArrayList<>(), nums);
        return ans;

    }

    public static void traceback(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -100) continue;
            int temp = nums[i];
            nums[i] = -100;
            list.add(temp);
            traceback(ans, list, nums);
            list.remove(list.size() - 1);
            nums[i] = temp;
        }

    }
}
