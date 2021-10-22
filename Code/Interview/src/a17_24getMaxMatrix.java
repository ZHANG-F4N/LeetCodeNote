import java.util.Arrays;

public class a17_24getMaxMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {9, -8, 1, 3, -2},
                {-3, 7, 6, -2, 4},
                {6, -4, -4, 8, -7}
        };
//        int[][] matrix = {
//                {4}
//        };
//        int[][] matrix = {
//                {-1, 0},
//                {0, -1}
//        };
        System.out.println(Arrays.toString(getMaxMatrix(matrix)));
    }

    public static int[] getMaxMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;


        // 每一列的前缀和

        for (int i = 0; i < M; ++i) {
            for (int j = 1; j < N; ++j) {
                matrix[j][i] += matrix[j - 1][i];
            }
        }
        int[] partSum = new int[M];

        int iLeft = 0;
        int jLeft = 0;
        int iRight = 0;
        int jRight = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    partSum[k] = i != 0 ? matrix[j][k] - matrix[i - 1][k] : matrix[j][k];
                }

                int[][] index = new int[M][2];
                int preDp = partSum[0];
                max = max > preDp ? max : preDp;
                index[0][0] = 0;
                index[0][1] = 0;
                int dp = 0;
                for (int l = 1; l < M; l++) {
                    int temp = preDp + partSum[l];
                    if (temp > partSum[l]) {

                        index[l][0] = index[l - 1][0];
                    } else {
                        index[l][0] = l;
                    }
                    index[l][1] = l;
                    dp = temp > partSum[l] ? temp : partSum[l];
                    preDp = dp;
                    if (dp > max) {
                        max = dp;
                        iLeft = i;
                        jLeft = index[l][0];
                        iRight = j;
                        jRight = index[l][1];
                    }
                }

            }
        }
        return new int[]{iLeft, jLeft, iRight, jRight};
    }
}
