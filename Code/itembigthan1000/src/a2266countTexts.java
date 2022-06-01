public class a2266countTexts {
    public static void main(String[] args) {
        System.out.println(countTexts("22233"));
        System.out.println(countTexts("222222222222222222222222222222222222"));
    }

    public static int countTexts(String pressedKeys) {
        int n = pressedKeys.length();
        char[] chars = pressedKeys.toCharArray();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < n; i++) {
//            dp[i] = chars[i] == '7' || chars[i] == '9' ? 4 : 3;
            int k = chars[i] == '7' || chars[i] == '9' ? 4 : 3;
            dp[i + 1] = dp[i];
            for (int j = 1; j < k; j++) {
                if (i - j >= 0 && chars[i - j] == chars[i]) {
                    dp[i + 1] = (dp[i + 1] + dp[i - j]) % 1000000007;
                } else {
                    break;
                }
            }

        }
        return (int) dp[n];
    }
}
