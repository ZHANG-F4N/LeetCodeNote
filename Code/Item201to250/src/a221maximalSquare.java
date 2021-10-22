public class a221maximalSquare {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    // dp dp[i][j] 表示以matrix[i][j]为右下角的最大正方形的边长
    // matrix[i][j] 必须包含
    public static int maximalSquare(char[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

//        int[][] colSum = new int[N][M];
//        int[][] rowSum = new int[N][M];
//        int ans = 0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                colSum[i][j] = i == 0 ? matrix[i][j] - '0' : colSum[i - 1][j] + matrix[i][j] - '0';
//                rowSum[i][j] = j == 0 ? matrix[i][j] - '0' : rowSum[i][j - 1] + matrix[i][j] - '0';
//            }
//        }
        int max = 0;
        int[][] dp = new int[N][M];
        for (int i = 0; i < M; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for (int i = 0; i < N; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }


        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }


        return max * max;
    }
}
