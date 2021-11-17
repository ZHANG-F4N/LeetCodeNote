import java.util.Arrays;

public class contest250a3 {
    public static void main(String[] args) {
        System.out.println(maxPoints(new int[][]{{1, 2, 3}, {1, 5, 1}, {3, 1, 1}}));
        System.out.println(maxPoints(new int[][]{{1, 2, 3}, {1, 5, 1}}));
    }

    public static long maxPoints(int[][] points) {



        int m = points[0].length;
        long[] pre = new long[m];
        long[] dp = new long[m];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            pre[i] = points[0][i];
            max = Math.max(max, points[0][i]);
        }
        if (points.length == 1) {
            return max;
        }
        for (int i = 1; i < points.length; i++) {
            //正向和反向计算一遍 dp[i-1][j']+j' 和 dp[i-1][j']-j'
            // j'<j 时,记录 0~j' 上 dp[i-1][j'] + j'的最大值
            //      同时算出 Max{ dp[i-1][j'] + j' } + points[i][j] - j
            // j'>j 时, 记录 j'~ m-1 上 dp[i-1][j'] - j'的最大值
            //      同时算出 Max{ dp[i-1][j'] - j' } + points[i][j] + j
            long[] forth = new long[m];
            long[] back = new long[m];
            long preMax = Long.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                forth[j] = pre[j] + j;
                preMax = Math.max(preMax, forth[j]);
                dp[j] = points[i][j] - j + preMax;
            }
            preMax = Long.MIN_VALUE;
            for (int j = m - 1; j >= 0; j--) {
                back[j] = pre[j] - j;
                preMax = Math.max(preMax, back[j]);
                dp[j] = Math.max(dp[j], points[i][j] + j + preMax);
            }
            pre = Arrays.copyOf(dp, m);
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;


    }

}
