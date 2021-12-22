public class a329longestIncreasingPath {
    public static void main(String[] args) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(longestIncreasingPath(matrix));
    }


    //优化

    public static int longestIncreasingPath(int[][] matrix) {
        int ans = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] lenMatrix = new int[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                DFS(matrix, lenMatrix, i, j);
                if (ans < lenMatrix[i][j]) {
                    ans = lenMatrix[i][j];
                }
            }
        }

        return ans + 1;
    }

    public static void DFS(int[][] matrix, int[][] lenMatrix, int i, int j) {
        //int[][] direct = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        if (lenMatrix[i][j] > 0) {
            return;
        }


        if (i + 1 < rows && matrix[i + 1][j] > matrix[i][j]) {
            DFS(matrix, lenMatrix, i + 1, j);
            lenMatrix[i][j] = Math.max(lenMatrix[i][j], lenMatrix[i + 1][j] + 1);
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            DFS(matrix, lenMatrix, i - 1, j);
            lenMatrix[i][j] = Math.max(lenMatrix[i][j], lenMatrix[i - 1][j] + 1);
        }
        if (j + 1 < cols && matrix[i][j + 1] > matrix[i][j]) {
            DFS(matrix, lenMatrix, i, j + 1);
            lenMatrix[i][j] = Math.max(lenMatrix[i][j], lenMatrix[i][j + 1] + 1);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            DFS(matrix, lenMatrix, i, j - 1);
            lenMatrix[i][j] = Math.max(lenMatrix[i][j], lenMatrix[i][j - 1] + 1);
        }
//      int[][] direct = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
//        for (int k = 0; k < direct.length; k++) {
//            int dx = i + direct[k][0];
//            int dy = j + direct[k][1];
//            if (dx < 0 || dx >= rows || dy < 0 || dy >= cols) {
//                continue;
//            }
//            if (matrix[dx][dy] > matrix[i][j]) {
//                if (lenMatrix[dx][dy] == 0) {
//                    DFS(matrix, lenMatrix, dx, dy);
//                }
//                lenMatrix[i][j] = Math.max(lenMatrix[i][j], lenMatrix[dx][dy] + 1);
//            }
//        }


    }
}
