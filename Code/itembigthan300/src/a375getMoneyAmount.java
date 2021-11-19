public class a375getMoneyAmount {
    public static void main(String[] args) {
        System.out.println(getMoneyAmount(1));
        System.out.println(getMoneyAmount(2));
        System.out.println(getMoneyAmount(3));
        System.out.println(getMoneyAmount(10));
    }

    static int N = 210;
    static int[][] cache = new int[N][N];

    public static int getMoneyAmount(int n) {
//        将初始化放在 全局会大大加快判题时间
//        cache = new int[n + 1][n + 1];
//        return dfs(4, 5);
//        dp

        /*
         * dp[1,3] = min{
         *  max{ dp[1,0],dp[2,3] } + 1  --  第一次 选1错
         *  max{ dp[1,1],dp[3,3] } + 2  --  第一次 选2错
         *  max{ dp[1,2],dp[4,3] } + 3  --  第一次 选3错
         *  }
         * */
        int[][] dp = new int[n + 2][n + 2];
        for (int j = 1; j < n; j++) {
            for (int i = 1; i <= n; i++) {
                if (i + j >n)break;
                dp[i][i + j] = Integer.MAX_VALUE;
                for (int k = i; k <= i + j; k++) {
                    dp[i][i + j] = Math.min(dp[i][i + j], Math.max(dp[i][k - 1], dp[k + 1][i + j]) + k);
                }
            }
        }

        return dp[1][n];
    }

    public static int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (cache[left][right] != 0) return cache[left][right];
        int ans = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            // 猜数 i
            // 需要左边的花费或者右边的花费
            // + i 是因为求绝对赢游戏的最小值,所以假设每次都猜错了,花费加上 i
            int cur = Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i;
            ans = Math.min(cur, ans);
        }
        cache[left][right] = ans;
        return ans;
    }

}
