public class a91numDecodings {
    public static void main(String[] args) {
        System.out.println(numDecodings("2611055971756562"));
    }

    public static int numDecodings(String s) {
        //含前导0就返回false
        int N = s.length();
        if (N == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int dp[] = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < N; i++) {
            char prCh = s.charAt(i - 1);
            char ch = s.charAt(i);
            if (ch == '0') {
                if (prCh == '2' || prCh == '1') {
                    dp[i + 1] = dp[i - 1];
                } else {
                    return 0;
                }
            } else if (prCh == '1' || (ch <= '6' && ch > '0') && prCh == '2') {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[N];
    }
}
