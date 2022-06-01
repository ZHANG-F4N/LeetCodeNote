import java.util.Arrays;
import java.util.HashSet;

public class a467findSubstringInWraproundString {
    public static void main(String[] args) {
        System.out.println(findSubstringInWraproundString("a"));
        System.out.println(findSubstringInWraproundString("cac"));
    }

    public static int findSubstringInWraproundString(String p) {
        int n = p.length();
        int[] dp = new int[26];
        char[] chars = p.toCharArray();
        int len = 0;
        for (int r = 0; r < n; r++) {
            if (r - 1 >= 0 && ((chars[r - 1] == 'z' && chars[r] == 'a') || chars[r] == chars[r - 1] + 1)) {
                len++;
            } else {
                len = 1;
            }
            dp[chars[r] - 'a'] = Math.max(dp[chars[r] - 'a'], len);
        }
        int ans = 0;
        for (int i : dp) ans += i;
        return ans;
    }
}
