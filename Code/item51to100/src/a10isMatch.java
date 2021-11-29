import java.util.ArrayList;
import java.util.HashMap;

public class a10isMatch {
    public static void main(String[] args) {
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));
        System.out.println(isMatch("aab", "c*a*b"));
    }

    /*
     *      ""  a   a   b
     *  ""  T   F   F   F
     * c*   T   F   F   F
     * a*   T   T   T   F
     *  b   F   F   F   T
     *
     * */

    public static boolean isMatch(String s, String p) {


        ArrayList<Character> chars = new ArrayList<>();
        StringBuilder stringP = new StringBuilder();

        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (i != p.length() - 1 && p.charAt(i + 1) == '*') {
                chars.add(ch);
                continue;
            }
            stringP.append(ch);
        }
        int n = stringP.length();
        int m = s.length();
        char[] arrS = s.toCharArray();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            dp[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            if (stringP.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = false;
            }
        }
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            char ch = stringP.charAt(i - 1);
            if (ch == '*') idx++;
            for (int j = 1; j <= m; j++) {
                if (ch == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (ch == '*') {
                    dp[i][j] = (arrS[j - 1] == chars.get(idx - 1)) && (dp[i - 1][j] || dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (ch == arrS[j - 1]);
                }

            }
        }
        return dp[n][m];


    }
}
















