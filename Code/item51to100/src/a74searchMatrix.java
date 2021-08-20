public class a74searchMatrix {
    public static void main(String[] args) {
        //int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int[][] matrix = {{1}, {3}};
        int target = 3;
        System.out.println(searchMatrix(matrix, target));

    }

    public static boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        //也可以使用使用二分
        for (int i = 0; i < m; i++) {
            if (target >= matrix[i][0]) {
                row = i;
            }
            if (target < matrix[i][0]){
                break;
            }
        }


        for (int i = 0; i < n; i++) {
            if (matrix[row][i] == target) {
                return true;
            }
        }
        return false;

    }
}
