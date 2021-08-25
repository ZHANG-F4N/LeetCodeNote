import java.lang.reflect.Array;
import java.util.Arrays;

public class a279numSquares {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(numSquares2(n));
    }
    //四平方定理
    public static int numSquares2(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;


    }

    // 判断是否为完全平方数
    public static boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public static boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }

    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        //Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }

        }
        return dp[n];
    }


}
