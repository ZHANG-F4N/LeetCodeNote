import java.util.ArrayList;

public class a322coinChange {
    public static void main(String[] args) {
        int[] coins = { 2};
        int amount = 1;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i - coins[j]], dp[i]) + 1;
                }
            }
        }
        if (dp[amount] == Integer.MIN_VALUE || dp[amount] == Integer.MAX_VALUE){
            return -1;
        }
        return dp[amount];
    }
}
