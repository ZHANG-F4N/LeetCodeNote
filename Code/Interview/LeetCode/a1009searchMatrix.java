public class a1009searchMatrix {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{}}, 5));
        searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5);

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        if (matrix.length == 0) return false;
        int j = matrix[0].length - 1;
        if (matrix[0].length == 0) return false;
        while (j >= 0 && i < matrix.length && matrix[i][j] != target) {
            while (matrix[i][j] < target) {
                i++;
                if (i >= matrix.length) return false;
            }
            while (matrix[i][j] > target) {
                j--;
                if (j < 0) return false;
            }
        }
        return true;
    }
}
