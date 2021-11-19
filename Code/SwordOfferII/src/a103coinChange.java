import java.util.Arrays;

public class a103coinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{2, 5, 10, 1}, 27));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{1, 2, 3}, 11));
    }

    /*
     *      0   1   2   3   4   5   6   7   8   9   10  11
     *  1   0   1   2   3   4   5   6   7   8   9   10  11
     *  2   0   1   1   2   2   3   3   4   4   5   5   6
     *  3   0   1   1   1   2   2   2   3   3   3   4   4
     * */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];

    }
}
