import java.util.Arrays;

public class a354maxEnvelopes {
    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{1, 8}, {5, 2}, {5, 4}, {6, 4}, {6, 7}, {2, 3}}));
    }

    public static int maxEnvelopes(int[][] envelopes) {

        /*
         * 先按 h 由小到大排列
         * - 再按 w 由 大 到 小排列
         * - 再在 w 上寻找最长递增子序列 这样求得序列就可以保证可以嵌套
         * 比如:
         *  h 1   2   5   5   6   6
         *  w 8   3  (4   2) (7   4)
         *       [3   4       7] 就是组成嵌套的序列
         * */

        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int n = envelopes.length;
        int[] dp = new int[n];

        int[] len = new int[n];
        for (int i = 0; i < envelopes.length; i++) len[i] = envelopes[i][1];


        // 可以使用二分来
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (len[i] > len[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;

    }

}
