import java.util.HashMap;
import java.util.LinkedHashMap;

public class a2055platesBetweenCandles {
    public static void main(String[] args) {
        System.out.println(platesBetweenCandles("||*", new int[][]{{2, 2}}));
        System.out.println(platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4,
                5}, {14, 17}}));
        System.out.println(platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}}));
    }

    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] plates = new int[n + 1];
        // lc 记录左侧最近的盘子
        // rc 记录右侧最近的盘子
        int[] lc = new int[n];
        int[] rc = new int[n];
        char[] chars = s.toCharArray();
        int l = -1, r = -1;
        for (int i = 0, j = n - 1; i < chars.length; i++, j--) {
            plates[i + 1] = plates[i] + (chars[i] == '*' ? 1 : 0);
            if (chars[i] == '|') l = i;
            if (chars[j] == '|') r = j;
            lc[i] = l;
            rc[j] = r;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            l = rc[queries[i][0]];
            r = lc[queries[i][1]];

            ans[i] = (l < r && l != -1 && r != -1) ? plates[r] - plates[l] : 0;
        }
        return ans;
    }
}
