public class a105maxAreaOfIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(grid));

    }

    public static int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ans = Math.max(ans, DFS(grid, i, j));
                }
            }
        }
        return ans;
    }

    public static int DFS(int[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0) {
            return 0;
        }
        grid[i][j] = 0;
        int num = 1;
        if (i + 1 < grid.length && grid[i + 1][j] == 1) num += DFS(grid, i + 1, j);
        if (i - 1 >= 0 && grid[i - 1][j] == 1) num += DFS(grid, i - 1, j);
        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) num += DFS(grid, i, j + 1);
        if (j - 1 >= 0 && grid[i][j - 1] == 1) num += DFS(grid, i, j - 1);
        return num;

    }
}