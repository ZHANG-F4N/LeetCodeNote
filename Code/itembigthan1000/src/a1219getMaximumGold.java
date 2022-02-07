public class a1219getMaximumGold {
    public static void main(String[] args) {
        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        System.out.println(getMaximumGold(grid));
    }


    public static int max = 0;

    public static int getMaximumGold(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return max;
    }

    // 回溯
    public static void dfs(int[][] grid, int i, int j, int ans) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            max = Math.max(max, ans);
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = 0;
        dfs(grid, i + 1, j, ans + temp);
        dfs(grid, i - 1, j, ans + temp);
        dfs(grid, i, j + 1, ans + temp);
        dfs(grid, i, j - 1, ans + temp);
        grid[i][j] = temp;


    }
}
