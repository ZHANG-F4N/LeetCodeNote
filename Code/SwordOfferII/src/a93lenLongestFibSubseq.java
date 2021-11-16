import java.util.HashMap;
import java.util.HashSet;

public class a93lenLongestFibSubseq {
    public static void main(String[] args) {
        System.out.println(lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        System.out.println(lenLongestFibSubseq(new int[]{1, 3, 7, 11, 12, 14, 18}));
    }


    /*
     *  1   2   3   5   8
     *      1   2   3   4   5   6   7   8
     *   1  1   2   2   2   2   2   2   2
     *   2      1   3   2   2   2   2   2
     *   3          1   3   4   2   2   2
     *   4              1   3   3   4   2
     *   5                  1   3   3   5
     *   6                      1   3   3
     *   7                          1   3
     *   8                              1
     *  dp[i][j]：表示以A[i],A[j]结尾的斐波那契数列的最大长度
     *              dp[i][j]=Len(......,A[i],A[j])
     *              A[k]+A[i]==A[j]
     *  dp[i][j] = max (dp[k][i]+1) 其中 A[k]+A[i]==A[j]
     * */
    public static int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        if (n == 0 || n == 1 || n == 2) {
            return 0;
        }
        int[][] dp = new int[n][n];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = 2;
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // i 开始 j 结束 A[j] - A[i] = A[k]
                if (hashMap.containsKey(arr[j] - arr[i])) {
                    int idx = hashMap.get(arr[j] - arr[i]);
                    dp[i][j] = Math.max(dp[i][j], dp[idx][i]+1);
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        return ans >= 3 ? ans : 0;

    }
}
