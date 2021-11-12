import java.util.*;

public class a82combinationSum2 {
    public static void main(String[] args) {
        combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
    }


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        // 为了避免重复答案
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        traceback(candidates, ans, list, 0, 0, target);
        return ans;
    }

    public static void traceback(int[] candidates, List<List<Integer>> ans,
                                 List<Integer> list, int idx, int sum,
                                 int target) {
        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }
//        if (idx == candidates.length) {
//            return;
//        }
        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            if (i > idx && candidates[i] == candidates[i-1]){
                // 因为前面那个同样的数已经经历过dfs，再拿同样的数dfs就会得到重复的答案
                continue;
            }
            list.add(candidates[i]);
            traceback(candidates, ans, list, i+1, sum + candidates[i], target);
            list.remove(list.size() - 1);
        }
    }
}
