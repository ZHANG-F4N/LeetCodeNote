import java.util.Arrays;

public class a0811waysToChange {
    public static void main(String[] args) {
        System.out.println(waysToChange(11));
    }

    public static int waysToChange(int n) {
        int[] coins = {1, 5, 10, 25};
        int[][] dp = new int[4][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j <= n; j++) {
                if (j - coins[i] >= 0) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i]]) % 10_0000_0007;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return  dp[3][n];

    }
}
