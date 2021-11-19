public class a95longestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("xaxx", "a"));
        System.out.println(longestCommonSubsequence("bl", "yby"));
        System.out.println(longestCommonSubsequence("abcde", "ace"));
    }
    /*
     *   a   b   c   d   e
     *   a   c   e
     *   dp[i][j] : A 以 A[i] 结尾 B以 B[j] 结尾的公共子序列长度，两个都选
     *          a   b   c   d   e
     *      ij  0   0   0   0   0
     *   a   0  1   1   1   1   1
     *   c   0  1   1   2   2   2
     *   e   0  1   1   2   2   3
     * */

    public static int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];

    }
}
