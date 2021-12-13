import java.awt.event.MouseAdapter;

public class a583minDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    public static int minDistance(String word1, String word2) {
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        int[][] dp = new int[arr1.length][arr2.length];
        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
        for (int i = 1; i < arr1.length; i++) {
            dp[i][0] = arr1[i] == arr2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < arr2.length; i++) {
            dp[0][i] = arr1[0] == arr2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                dp[i][j] = arr1[i] == arr2[j] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return arr1.length + arr2.length - 2 * dp[arr1.length - 1][arr2.length - 1];
    }
}
