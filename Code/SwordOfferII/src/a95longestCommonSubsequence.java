public class a95longestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }
    /*
     *   a   b   c   d   e
     *
     *   a   c   e
     *   dp[i][j] : A 以 A[i] 结尾 B以 B[j] 结尾的公共子序列长度，两个都选
     *       a   b   c   d   e
     *   ij  0   1   2   3   4
     * a 0   1   1   1   1   1
     * c 1   1   1   2   2   2
     * e 2   1   1   2   2   3
     * */

    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < m; i++) {
            // 可能越界
            char ch1 = text1.charAt(i);
            char ch2 = text2.charAt(i);
            dp[0][i] = ch1 == ch2 ? 1 : 0;
            if (i != 0) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[0][i]);
            }

        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch1 = text1.charAt(i);
                char ch2 = text2.charAt(j);
                dp[i][j] = ch1 == ch2 ? dp[i][j - 1] + 1 : dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];

    }
}
