public class a92minFlipsMonoIncr {
    public static void main(String[] args) {
        System.out.println(minFlipsMonoIncr2("00110"));
        System.out.println(minFlipsMonoIncr2("010110"));
        System.out.println(minFlipsMonoIncr("00011000"));
    }

    public static int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] pre = new int[n];
        int[] dp = new int[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            num += s.charAt(i) - '0';
            pre[i] = num;
        }

        dp[0] = n - pre[n - 1];
        dp[n - 1] = pre[n - 1];
        int min = Math.min(dp[0], dp[n - 1]);
        for (int i = 1; i < n - 1; i++) {
            dp[i] = pre[i - 1] + (n - 1 - i - pre[n - 1] + pre[i]);
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    public static int minFlipsMonoIncr2(String s) {
        int f0 = 0;
        int f1 = 0;
        for (char c : s.toCharArray()) {
            f0 += c - '0';// 多少个1
            f1 = Math.min(f0, f1 + 1 - (c - '0')); // 前面 1 的数量
        }
        return f1;
    }
}
