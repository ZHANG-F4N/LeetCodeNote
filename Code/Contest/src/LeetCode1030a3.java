import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class LeetCode1030a3 {
    public static void main(String[] args) {
        System.out.println(platesBetweenCandles("**|**|***|", new int[][]{{3, 11}, {5, 9}}));
    }

    public static int[] platesBetweenCandles(String s, int[][] queries) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '|') {
                list.add(i);
            }
        }
        int[] idx = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            idx[i] = list.get(i);
        }

        int N = queries.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int beg = queries[i][0];
            int end = queries[i][1];

            int index1 = Arrays.binarySearch(idx, beg);
            int index2 = Arrays.binarySearch(idx, end);
            // 可能越界
            if (index1 < 0) {
                index1 = (-index1) - 1;
            }
            if (index2 < 0) {
                index2 = (-index2) - 1;
            }
            int temp = idx[index2] - idx[index1] - (index2 - index1);
            ans[i] = temp;
        }

        return ans;

    }
}
