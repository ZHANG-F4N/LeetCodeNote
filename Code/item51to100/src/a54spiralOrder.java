import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a54spiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List ans = spiralOrder(matrix);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int i = 0, j = 0;
        ans.add(matrix[0][0]);
        while (up <= down && left <= right) {
            while (j < right && up <= down) {
                j++;
                ans.add(matrix[i][j]);
            }
            up++;
            while (i < down && left <= right) {
                i++;
                ans.add(matrix[i][j]);
            }
            right--;

            while (j > left && up <= down) {
                j--;
                ans.add(matrix[i][j]);
            }
            down--;

            while (i > up && left <= right) {
                i--;
                ans.add(matrix[i][j]);
            }
            left++;
        }
        return ans;
    }
}
