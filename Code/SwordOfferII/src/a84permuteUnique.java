import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a84permuteUnique {
    public static void main(String[] args) {
        permuteUnique(new int[]{1, 1, 2});
        permuteUnique(new int[]{1, 2, 3});

    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
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
            // 排序后两个相同的数会相邻
            // 第一个数肯定会在第一次访问时将第一个数首次出现的情况全部包含
            // 当访问第二个数且第一个数没有访问过时,只需要跳过即可,因为这种情况
            // 已经在之前出现过
            if (i != 0 && nums[i] == nums[i-1] &&  nums[i-1] != -100)continue;
            int temp = nums[i];
            nums[i] = -100;
            list.add(temp);
            traceback(ans, list, nums);
            list.remove(list.size() - 1);
            nums[i] = temp;
        }
    }
}
