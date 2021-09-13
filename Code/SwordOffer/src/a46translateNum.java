import java.util.HashMap;

public class a46translateNum {
    public static void main(String[] args) {
        //给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
        // 1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
        // 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

        int num = 506;
        System.out.println(translateNum(num));
    }

    public static int translateNum(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            if (num <= 25) {
                return 2;
            }
            return 1;
        }
        int[] dp = new int[str.length()];
        dp[0] = 1;
        int temp = Integer.parseInt(str.substring(0, 2));
        dp[1] = temp <= 25  && temp >= 10? 2 : 1;
        for (int i = 2; i < n; i++) {
            temp = Integer.parseInt(str.substring(i - 1, i + 1));
            if (temp >= 10 && temp <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n - 1];
    }
}
