import java.util.ArrayList;
import java.util.List;

public class a81combinationSum {
    public static void main(String[] args) {
        combinationSum(new int[]{2, 3, 6, 7}, 7);
        combinationSum(new int[]{2, 3, 5}, 8);

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback2(ans, new ArrayList<>(), candidates, 0, target);
        return ans;
    }

    public static void traceback(List<List<Integer>> ans, List<Integer> list, int[] candidates,
                                 int idx, int target) {
        if (target < 0 || idx >= candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(candidates[idx]);
        // 加上当前这个数
        traceback(ans, list, candidates, idx, target - candidates[idx]);
        list.remove(list.size() - 1);
        if (idx + 1 >= candidates.length) {
            return;
        }
        // 不加当前这个数
        traceback(ans, list, candidates, idx + 1, target);
    }

    /*
    * 这里的 target 是不选择 candidate[idx] 的情况
    * */
    public static void traceback2(List<List<Integer>> ans, List<Integer> list, int[] candidates,
                                  int idx, int target) {
        if (target < 0) {
            return;
        }
        // 这里的 target 是不选择 candidate[idx] 的情况
        // 进行处理
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // 后面是选当前idx的情况
        // idx 之前的数字情况已经全部处理
        for (int i = idx; i < candidates.length; i++) {
            list.add(candidates[i]);
            traceback(ans, list, candidates, i, target - candidates[i]);
//            此时如果从 i+1 开始,则是不重复选的情况
//            traceback(ans, list, candidates, i+1, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
