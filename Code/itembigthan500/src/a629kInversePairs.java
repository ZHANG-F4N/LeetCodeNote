public class a629kInversePairs {
    public static void main(String[] args) {
        System.out.println(kInversePairs(3, 1));
        System.out.println(kInversePairs(4, 2));
        System.out.println(kInversePairs(4, 3));
    }

    public static int kInversePairs(int n, int k) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[1][i] = i - 1;
        }
        for (int i = 1; i <= k; i++) {
            dp[i][1] = 0;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 0; j <= n; j++) {

                dp[i][j] = dp[i - 1][j]  + dp[i][j - 1];
            }
        }


        return dp[k][n];
    }
}
