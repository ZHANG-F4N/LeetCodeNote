import java.util.Map;

public class a883projectionArea {
    public static void main(String[] args) {
        System.out.println(projectionArea(new int[][]{{1, 2}, {3, 4}}));
    }

    public static int projectionArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int xy = 0;
        int yz = 0;
        int zx = 0;

        for (int i = 0; i < n; i++) {
            int rowMax = 0;
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 0) xy++;
                rowMax = Math.max(rowMax, grid[i][j]);
            }
            zx += rowMax;
        }

        for (int j = 0; j < m; j++) {
            int colMax = 0;
            for (int i = 0; i < n; i++) {
                colMax = Math.max(colMax, grid[i][j]);
            }
            yz += colMax;
        }
        return xy + yz + zx;

    }
}
