import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a0802pathWithObstacles {
    public static void main(String[] args) {
        pathWithObstacles2(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });
    }


    // dp
    public static List<List<Integer>> pathWithObstacles2(int[][] obstacleGrid) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (n - 1 == 0 && m - 1 == 0 && obstacleGrid[0][0] ==1) return ans;
        if (n - 1 == 0 && m - 1 == 0 && obstacleGrid[0][0] ==0) {
            ans.add(Arrays.asList(new Integer[]{0, 0}));
            return ans;
        }
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int j = 1; j < m; j++) {
            if (obstacleGrid[0][j] == 1) dp[0][j] = 0;
            else dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] | dp[i - 1][j];
                }
            }
        }
        // 还原路径
        int i = n - 1, j = m - 1;
        if (dp[i][j] == 0) return ans;
        ans.add(Arrays.asList(new Integer[]{i, j}));
        while (ans.size() != n + m - 1) {
            if (dp[i][j] == 1) {
                if (i - 1 >= 0 && dp[i - 1][j] == 1) {
                    ans.add(Arrays.asList(new Integer[]{i - 1, j}));
                    i--;
                    continue;
                }
                if (j - 1 >= 0 && dp[i][j - 1] == 1) {
                    ans.add(Arrays.asList(new Integer[]{i, j - 1}));
                    j--;
                    continue;
                }
            }
        }
        for (int k = 0; k < ans.size() >> 1; k++) {
            List<Integer> temp = ans.get(k);
            ans.set(k, ans.get(ans.size() - k - 1));
            ans.set(ans.size() - k - 1, temp);
        }
        return ans;
    }

    public static List<List<Integer>> ans;

    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        ans = new ArrayList<>();
        DFS(obstacleGrid, 0, 0, new ArrayList<List<Integer>>());
        return ans;
    }

    public static void DFS(int[][] obstacle, int i, int j, List<List<Integer>> tempAns) {
        if (i < 0 || i >= obstacle.length || j < 0 || j >= obstacle[0].length || obstacle[i][j] == 1)
            return;
        obstacle[i][j] = 1;
        tempAns.add(Arrays.asList(new Integer[]{i, j}));
        if (i == obstacle.length - 1 && j == obstacle[0].length - 1) {
            Iterator<List<Integer>> it = tempAns.iterator();
            while (it.hasNext()) {
                ans.add(it.next());
            }
            return;
        }
        DFS(obstacle, i + 1, j, tempAns);
        DFS(obstacle, i, j + 1, tempAns);
        tempAns.remove(tempAns.size() - 1);
    }
}
