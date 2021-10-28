public class a13NumMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix obj = new NumMatrix(matrix);
        int param_1 = obj.sumRegion(2, 1, 4, 3);
        System.out.println(param_1);
    }
}

class NumMatrix {
    int[][] preSum;
    int N;
    int M;

    public NumMatrix(int[][] matrix) {
        N = matrix.length;
        M = matrix[0].length;
        preSum = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                preSum[i][j] = j == 0 ? matrix[i][j] : preSum[i][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = 0;
        for (int i = row1; i <= row2; i++) {
            ans += col1 == 0 ? preSum[i][col2] : preSum[i][col2] - preSum[i][col1-1];
        }
        return ans;
    }
}
