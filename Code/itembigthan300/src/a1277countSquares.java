import java.awt.event.MouseAdapter;

public class a1277countSquares {
    public static void main(String[] args) {
//        int[][] matrix =
//                {
//                        {0, 1, 1, 1},
//                        {1, 1, 1, 1},
//                        {0, 1, 1, 1}
//                };
        int[][] matrix =
                {
                        { 1, 0, 1},
                        { 1, 1, 0},
                        { 1, 1, 0}
                };
        System.out.println(countSquares(matrix));
    }


    public static int countSquares(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                ans++;
            }

        }

        for (int i = 1; i < M; i++) {
            if (matrix[0][i] == 1) {
                dp[0][i] = 1;
                ans++;
            }
        }



        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    ans += dp[i][j];
                }
            }
        }
        return ans;


    }
}
