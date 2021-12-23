import java.util.ArrayList;
import java.util.Arrays;

public class a1619pondSizes {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(pondSizes(new int[][]{
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1}
        })));
    }

    public static int area;

    public static int[] pondSizes(int[][] land) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 0) {
                    area = 0;
                    dfs(land, i, j);
                    ans.add(area);
                }
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    public static void dfs(int[][] land, int i, int j) {
        if (i < 0 || j < 0 || i >= land.length || j >= land[0].length || land[i][j] != 0)
            return;
        area++;
        land[i][j] = 1;
        dfs(land, i - 1, j - 1);
        dfs(land, i - 1, j);
        dfs(land, i - 1, j + 1);
        dfs(land, i, j - 1);
        dfs(land, i, j + 1);
        dfs(land, i + 1, j - 1);
        dfs(land, i + 1, j);
        dfs(land, i + 1, j + 1);
    }
}
