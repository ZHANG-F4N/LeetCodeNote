public class a807maxIncreaseKeepingSkyline {
    public static void main(String[] args) {
        System.out.println(maxIncreaseKeepingSkyline(new int[][]{{3, 0, 8, 4}, {2, 4, 5, 7}, {9,
                2, 6, 3}, {0, 3, 1, 0}}));
    }

    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] left = new int[n];
        int[] up = new int[m];
        for (int i = 0; i < n; i++) {
            left[i] = grid[i][0];
            for (int j = 0; j < m; j++) {
                left[i] = Math.max(left[i], grid[i][j]);
            }
        }
        for (int j = 0; j < m; j++) {
            up[j] = grid[0][j];
            for (int i = 0; i < n; i++) {
                up[j] = Math.max(up[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += Math.min(up[j], left[i]) - grid[i][j];
            }
        }
        return ans;


    }
}
