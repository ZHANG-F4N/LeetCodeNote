import java.util.Map;

public class LeetCode0926a2 {
    public static void main(String[] args) {
//        int[] nums = {7, 7, 5, 4};
        int[][] grid = {{1, 3, 1, 15}, {1, 3, 3, 1}};
//        [[20,3,20,17,2,12,15,17,4,15],[20,10,13,14,15,5,2,3,14,3]]
//        int[][] grid = {{20, 3, 20, 17, 2, 12, 15, 17, 4, 15}, {20, 10, 13, 14, 15, 5, 2, 3, 14, 3}};//63
        System.out.println(gridGame(grid));
    }

    public static long gridGame(int[][] grid) {
        int N = grid[0].length;
        if (N == 1) {
            return 0;
        }
        long[][] temp = new long[2][N];
        temp[0][N - 1] = grid[0][N - 1];
        temp[1][0] = grid[1][0];

        for (int i = 1; i < N; i++) {
            temp[1][i] = temp[1][i - 1] + grid[1][i];
        }
        for (int j = N - 2; j >= 0; j--) {
            temp[0][j] = temp[0][j + 1] + grid[0][j];
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            long pathMax = 0;
            if (i == 0) {
                pathMax = temp[0][1];
            } else if (i == N - 1) {
                pathMax = temp[1][N - 2];
            } else {
                pathMax = Math.max(temp[0][i + 1], temp[1][i - 1]);
            }
            ans = Math.min(ans, pathMax);
        }
        return ans;
    }

}
