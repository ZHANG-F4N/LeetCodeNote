import java.util.HashMap;

public class a1034colorBorder {
    public static void main(String[] args) {
        System.out.println(colorBorder(new int[][]{{2, 1, 3, 2, 1, 1, 2}, {1, 2, 3, 1, 2, 1, 2}, {1, 2, 1, 2, 2, 2,
                2}, {2, 1, 2, 2, 2, 2, 2}, {2, 3, 3, 3, 2, 1, 2}}, 4, 4, 3));
        System.out.println(colorBorder(new int[][]{{1, 1}, {1, 2}}, 0, 0, 3));
        System.out.println(colorBorder(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}
                , 1, 1, 2));
        System.out.println(colorBorder(new int[][]{{1, 1, 1, 1}, {1, 1, 2, 1}, {1, 2, 2, 1}, {1, 1, 2, 1}}, 1, 1, 3));
    }

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid[row][col] == color) {
            return grid;
        }
        int temp = grid[row][col];
        DFS(grid, row, col, grid[row][col], color);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1) {
                    grid[i][j] = color;
                }
                if (grid[i][j] == -2) {
                    grid[i][j] = temp;
                }
            }
        }
        return grid;
    }

    public static void DFS(int[][] grid, int i, int j, int paint, int color) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1 || grid[i][j] != paint || grid[i][j] == -2) {
            return;
        }
        // 数组边界
        if (i - 1 < 0 || i + 1 >= grid.length || j - 1 < 0 || j + 1 >= grid[0].length) {
            grid[i][j] = -1;
            // 联通分量边界
        } else if ((grid[i - 1][j] != paint && grid[i - 1][j] != -1 && grid[i - 1][j] != -2) ||
                (grid[i + 1][j] != paint && grid[i + 1][j] != -1 && grid[i + 1][j] != -2) ||
                (grid[i][j - 1] != paint && grid[i][j - 1] != -1 && grid[i][j - 1] != -2) ||
                (grid[i][j + 1] != paint && grid[i][j + 1] != -1 && grid[i][j + 1] != -2)) {
            grid[i][j] = -1;
        } else {
            // 被访问 且 为内部
            grid[i][j] = -2;
        }
        DFS(grid, i - 1, j, paint, color);
        DFS(grid, i + 1, j, paint, color);
        DFS(grid, i, j - 1, paint, color);
        DFS(grid, i, j + 1, paint, color);
    }
}
