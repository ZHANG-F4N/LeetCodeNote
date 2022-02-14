public class a1020numEnclaves {
    public static void main(String[] args) {
        System.out.println(numEnclaves(new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}}));
    }

    public static int numEnclaves(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 1) dfs(grid, 0, i);
            if (grid[n - 1][i] == 1) dfs(grid, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][m - 1] == 1) dfs(grid, i, m - 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)ans++;
            }
        }


        return ans;
    }

    public static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0|| grid[i][j] == -1) {
            return;
        }
        grid[i][j] = -1;
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);

    }
}
