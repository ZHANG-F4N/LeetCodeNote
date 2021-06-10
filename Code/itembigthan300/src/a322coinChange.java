import java.util.ArrayList;
import java.util.Arrays;

public class a322coinChange {
    public static void main(String[] args) {
        int[] coins = { 2,5,10,1};
        int amount = 27;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        int max = amount+1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        //API自带,但费时间
        //Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i - coins[j]]+1, dp[i]) ;
                }
            }
        }
        return dp[amount] > amount ? -1 :dp[amount];
    }
}
