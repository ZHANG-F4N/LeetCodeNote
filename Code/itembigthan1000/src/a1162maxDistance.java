import java.util.ArrayDeque;

public class a1162maxDistance {
    public static void main(String[] args) {
        System.out.println(maxDistance(new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}}
        ));

    }

    public static int maxDistance(int[][] grid) {
        int ans = -1;
        int n = grid.length;
        int m = grid[0].length;
        int[][] tMap = new int[n][m];
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 添加源节点
                // ...
                if (grid[i][j] == 1) deque.offer(new int[]{i, j});
                tMap[i][j] = grid[i][j] == 1 ? -1 : 0;
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!deque.isEmpty()) {
            int[] pos = deque.poll();
            int x = pos[0], y = pos[1];
            int step = Math.max(tMap[x][y], 0);
            for (int[] dir : dirs) {
                // 对单个节点进行访问
                int dx = x + dir[0], dy = y + dir[1];
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                if (tMap[dx][dy] == -1 || tMap[dx][dy] != 0) continue;
                tMap[dx][dy] = step + 1;
                ans = Math.max(ans, tMap[dx][dy]);
                // 扩散
                deque.offer(new int[]{dx, dy});
            }
        }

        return ans;
    }
}
