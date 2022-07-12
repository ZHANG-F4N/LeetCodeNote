import java.util.HashMap;

public class a873lenLongestFibSubseq {
    public static void main(String[] args) {

    }

    public static int lenLongestFibSubseq(int[] arr) {
        int n = arr.length, ans = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) hashMap.put(arr[i], i);
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] - arr[j] >= arr[j]) break;
                int idx = hashMap.getOrDefault(arr[i] - arr[j], -1);
                if (idx == -1) continue;
                dp[i][j] = Math.max(3, dp[j][idx] + 1);
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans;





    }
}
