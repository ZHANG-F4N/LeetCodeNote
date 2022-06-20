import java.util.Arrays;

public class contest297 {
    public static void main(String[] args) {
        System.out.println(calculateTax(new int[][]{{1, 0}, {4, 25}, {5, 50}}, 2));
        System.out.println(calculateTax(new int[][]{{3, 50}, {7, 10}, {12, 25}}, 10));

        System.out.println(minPathCost(new int[][]{{5, 3}, {4, 0}, {2, 1}}, new int[][]{{9, 8},
                {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}}));

    }

    //Q1
    public static double calculateTax(int[][] brackets, int income) {
        int n = brackets.length;

        double ans = 0L;
        if (income > brackets[0][0]) ans += brackets[0][0] * brackets[0][1];
        else {
            ans += income * brackets[0][1];
            return ans / 100;
        }

        for (int i = 1; i < n; i++) {
            if (income > brackets[i][0])
                ans += (brackets[i][0] - brackets[i - 1][0]) * brackets[i][1];
            else {
                ans += (income - brackets[i - 1][0]) * brackets[i][1];
                return ans / 100;
            }

        }
        if (income > brackets[n - 1][0]) {
            ans += (income - brackets[n - 1][0]) * brackets[n - 1][1];
        }
        return ans / 100;
    }

    //Q2
    public static int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    min = Math.min(min, dp[i - 1][k] + moveCost[grid[i - 1][k]][j]);
                }
                dp[i][j] = min + grid[i][j];
            }
        }

        int ans = dp[m - 1][0];
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i]);
        }
        return ans;
    }
    //Q3

}
