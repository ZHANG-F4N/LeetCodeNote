public class a04findNumberIn2DArray {
    public static void main(String[] args) {

        int[][] nums = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int tar = 20;
        System.out.println(findNumberIn2DArray(nums, tar));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int i = matrix.length - 1;
        int j = 0;

        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            }
            while (i >= 0 && j < matrix[0].length && matrix[i][j] < target) {
                j++;
            }
            while (i >= 0 && j < matrix[0].length && matrix[i][j] > target) {
                i--;
            }
        }
        return false;

    }
}
