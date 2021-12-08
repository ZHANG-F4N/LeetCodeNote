import java.util.HashSet;
import java.util.Iterator;

public class a0108setZeroes {
    public static void main(String[] args) {
        setZeroes(new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}});
    }

    public static void setZeroes(int[][] matrix) {
        HashSet<Integer> colSet = new HashSet<>();
        HashSet<Integer> rowSet = new HashSet<>();
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        Iterator<Integer> it = rowSet.iterator();
        while (it.hasNext()) {
            int row = it.next();
            for (int i = 0; i < m; i++) {
                matrix[row][i] = 0;
            }
        }
        it = colSet.iterator();

        while (it.hasNext()) {
            int col = it.next();
            for (int i = 0; i < m; i++) {
                matrix[i][col] = 0;
            }
        }
    }
}
