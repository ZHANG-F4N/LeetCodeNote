import java.util.Arrays;

public class a29spiralOrder {
    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix = {{1, 2, 3}, {4,5, 6}, {7,8,9}};
//        int[][] matrix = {{6, 9, 7}};
        System.out.println(Arrays.toString(spiralOrder(matrix)));
    }

    public static int[] spiralOrder(int[][] matrix) {

        if (matrix.length == 0) {
            return new int[]{};
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        int[] ans = new int[(bottom + 1) * (right + 1)];
        int index = 0;

        int i = 0;
        int j = 0;
        while (left <= right && top <= bottom) {
//            i = top;
//            j = left;
            while (j < right && left <= right && top <= bottom) {
                ans[index] = matrix[i][j];
                j++;
                index++;
            }
            top++;
            while (i < bottom && top <= bottom && left <= right) {
                ans[index] = matrix[i][j];
                i++;
                index++;
            }
            right--;
            while (j > left && left <= right && top <= bottom) {
                ans[index] = matrix[i][j];
                j--;
                index++;
            }
            bottom--;
            while (i > top && top <= bottom && left <= right) {
                ans[index] = matrix[i][j];
                i--;
                index++;
            }
            left++;
        }
        if (index < ans.length) {
            ans[index] = matrix[i][j];
        }

        return ans;
    }
}
