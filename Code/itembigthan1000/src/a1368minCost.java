import java.util.ArrayDeque;
import java.util.Arrays;

public class a1368minCost {
    public static void main(String[] args) {
        System.out.println(minCost(new int[][]{{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}}));
    }

    public static int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        var cost = new int[n][m];
        var dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < n; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        cost[0][0] = 0;
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] p = deque.pollFirst();
            int x = p[0], y = p[1];
            for (int[] d : dir) {
                int dx = x + d[0], dy = y + d[1];
                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
                int val = 1;
                if (grid[x][y] == 1 && d[0] == 0 && d[1] == 1) val = 0;
                if (grid[x][y] == 2 && d[0] == 0 && d[1] == -1) val = 0;
                if (grid[x][y] == 3 && d[0] == 1 && d[1] == 0) val = 0;
                if (grid[x][y] == 4 && d[0] == -1 && d[1] == 0) val = 0;
                if (cost[dx][dy] > val + cost[x][y]) {
                    cost[dx][dy] = val + cost[x][y];
                    if (val == 0) deque.addFirst(new int[]{dx, dy});
                    else deque.addLast(new int[]{dx, dy});
                }
            }


        }
        return cost[n - 1][m - 1];
    }
}
