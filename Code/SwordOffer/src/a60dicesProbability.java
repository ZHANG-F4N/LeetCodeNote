import javax.sound.sampled.EnumControl;
import java.util.Arrays;

public class a60dicesProbability {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dicesProbability(2)));
    }

    //dp
    public static double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6);
        for (int i = 2; i <= n; i++) {
            double[] ans = new double[i * 5 + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    ans[j + k] += dp[j] / 6.0;
                }
            }
            dp = ans;
        }
        return dp;

    }

    //暴力法 6进制 超时 淦
    public static double[] dicesProbability2(int n) {
        int[] num = new int[n + 1];

        int[] sum = new int[n * 6 + 1];
        Arrays.fill(num, 1);
        while (num[0] <= 1) {
            int temp = 0;
            for (int j = 1; j <= n; j++) {
                temp += num[j];
            }
            sum[temp]++;

            int carry = n;
            num[n] = num[n] + 1;
            while (carry > 0 && num[carry] > 6) {
                num[carry] = 1;
                carry--;
                num[carry] = num[carry] + 1;
            }
        }
        double[] ans = new double[n * 6 - n + 1];
        int count = 0;
        for (int i = n; i <= n * 6; i++) {
            count += sum[i];
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (sum[i + n] / (double) count);
        }
        return ans;
    }


}
