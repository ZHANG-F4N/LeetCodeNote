import java.util.ArrayList;

public class a44isMatch {
    public static void main(String[] args) {

        System.out.println(isMatch("cb", "?a"));
        System.out.println(isMatch("aa", "*"));
        System.out.println(isMatch("acdcb", "a*c?b"));
    }

    /*
     *      ""  a   c   d   c   b
     *  ""  T   F   F   F   F   F
     *  a   F   T   F   F   F   F
     *  *   F   T   T   T   T   T
     *  c   F   F   T   F   T   F
     *  ?   F   F   F   T   F   T
     *  b   F
     *
     * */
    public static boolean isMatch(String s, String p) {
        // 动态规划
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();
        int n = arrP.length;
        int m = arrS.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // 第一行
        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            if (arrP[i - 1] != '*') {
                dp[i][0] = false;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arrP[i - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (arrP[i - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (arrP[i - 1] == arrS[j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}
