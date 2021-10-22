import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a77combine {
    public static void main(String[] args) {
        combine(4, 2);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
//        List<Integer> integers = new ArrayList<>();
        Integer[] tempAns = new Integer[k];
        traceback(ans, tempAns, n, 0, k, 1);
        return ans;
    }

    public static void traceback(List<List<Integer>> ans, Integer[] tempAns, int n, int len, int k, int i) {

        if (len == k) {
            ArrayList<Integer> temp = new ArrayList();
            for (Integer tempAn : tempAns) {
                temp.add(tempAn);
            }
            ans.add(temp);
            return;
        }
        if (i > n) {
            return;
        }
        tempAns[len] = i;
        traceback(ans, tempAns, n, len + 1, k, i + 1);
        tempAns[len] = null;
        traceback(ans, tempAns, n, len, k, i + 1);
    }
}
