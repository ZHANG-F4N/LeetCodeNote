import java.util.Arrays;
import java.util.HashSet;

public class contest250 {
    public static void main(String[] args) {
        System.out.println(addRungs(new int[]{1, 3, 5, 10}, 2));
        System.out.println(addRungs(new int[]{3}, 1));
        System.out.println(addRungs(new int[]{5}, 10));

        System.out.println(addRungs(new int[]{1, 3, 5, 10}, 3));
    }

    public static int addRungs(int[] rungs, int dist) {

        int ans = 0;
        int dis = rungs[0];
        if (dis > dist) {
            ans += (dis - 0.5) / dist;
        }

        for (int i = 1; i < rungs.length; i++) {
            dis = rungs[i] - rungs[i - 1];
            if (dis > dist) {
                ans += (dis - 0.5) / dist;
            }
        }
        return ans;
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
    public static int canBeTypedWords(String text, String brokenLetters) {
        HashSet<Character> set = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            set.add(c);
        }
        int ans = 0;
        for (String s : text.split(" ")) {
            boolean flag = true;
            for (char c : s.toCharArray()) {
                if (set.contains(c)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }

}
