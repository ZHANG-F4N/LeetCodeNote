import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a0812solveNQueens {
    public static void main(String[] args) {
        solveNQueens(4);
    }

    // 枚举行 ,三个数组标记 列 对角线 反对角线
    public static boolean col[] = new boolean[100];
    public static boolean dg[] = new boolean[100];
    public static boolean udg[] = new boolean[100];

    public static List<List<String>> ans;

    public static List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        ArrayList<Character[]> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Character[] map = new Character[n];
            Arrays.fill(map, '.');
            strings.add(map);
        }
        backtrack(0, n, strings);
        return ans;
    }

    public static void backtrack(int i, int n, List<Character[]> str) {
        if (i == n) {
            List<String> temp = new ArrayList<>();
            Iterator<Character[]> it = str.iterator();
            while (it.hasNext()) {
                Character[] p = it.next();
                StringBuilder string = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    string.append(p[k]);
                }
                temp.add(string.toString());
            }
            ans.add(temp);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col[j] && !dg[j - i + n] && !udg[j + i]) {
                col[j] = true;
                dg[j - i + n] = true;
                udg[j + i] = true;
                str.get(i)[j] = 'Q';
                backtrack(i + 1, n, str);
                str.get(i)[j] = '.';
                col[j] = false;
                dg[j - i + n] = false;
                udg[j + i] = false;
            }
        }
    }
}
