public class a240searchMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        System.out.println(searchMatrix(matrix, target));

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length;
        int cTemp = 0;
        int rTemp = matrix.length - 1;
        while (cTemp < col && rTemp >= 0) {
            if (matrix[rTemp][cTemp] == target) {
                return true;
            }
            if (matrix[rTemp][cTemp] > target) {
                rTemp--;
                continue;
            }
            if (matrix[rTemp][cTemp] < target) {
                cTemp++;
                continue;
            }
        }
        return false;
    }
}
