import java.util.ArrayDeque;
import java.util.Deque;

public class a1765highestPeak {
    public static void main(String[] args) {
        int[][] isWater = {{0, 1}, {0, 0}};
        highestPeak(isWater);
    }

    public static int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;
        int[][] ans = new int[n][m];
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) queue.offer(new int[]{i, j});
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            for (int i = 0; i < dirs.length; i++) {
                int dx = x + dirs[i][0], dy = y + dirs[i][1];
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                if (ans[dx][dy] != -1) continue;
                ans[dx][dy] = ans[x][y] + 1;
                queue.offer(new int[]{dx,dy});
            }
        }
        return ans;
    }


}
