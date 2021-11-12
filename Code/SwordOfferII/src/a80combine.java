import java.util.ArrayList;
import java.util.List;

public class a80combine {
    public static void main(String[] args) {
        combine(4, 2);
        combine(1, 1);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        traceback2(ans, new ArrayList<>(), 1, n, k);
        return ans;
    }

    public static void traceback(List<List<Integer>> ans, List<Integer> list, int idx, int n,
                                 int k) {
        if (n - idx + 1 < k)return;
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i <= n; i++) {
            list.add(i);
            traceback(ans, list, i + 1, n, k - 1);
            list.remove(list.size() - 1);
        }
    }
    public static void traceback2(List<List<Integer>> ans, List<Integer> list, int idx, int n,
                                  int k) {
        if (n - idx + 1 < k)return;
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(idx);
        traceback(ans, list, idx + 1, n, k - 1);
        list.remove(list.size() - 1);
        traceback(ans, list, idx + 1, n, k );
    }
}
