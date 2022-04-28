import java.util.*;

public class a417pacificAtlantic {
    public static void main(String[] args) {
        pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1,
                4, 5},{5,1,1,2,4}});
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        HashSet<Integer> pointPacific = new HashSet<>();
        HashSet<Integer> pointAtlantic = new HashSet<>();
        int n = heights.length;
        int m = heights[0].length;
        int[][] map = new int[n][m];

        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            pointPacific.add(0 * 1000 + i);
            pointAtlantic.add((n - 1) * 1000 + i);
            deque1.addLast(0 * 1000 + i);
            deque2.addLast((n - 1) * 1000 + i);
            map[0][i] = -1;

        }
        for (int i = 0; i < n; i++) {
            pointPacific.add(i * 1000 + 0);
            pointAtlantic.add(i * 1000 + m - 1);
            deque1.addLast(i * 1000 + 0);
            deque2.addLast(i * 1000 + m - 1);
            map[i][0] = -1;
        }
        int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        while (!deque1.isEmpty()) {
            Integer point = deque1.removeFirst();
            int x = point / 1000;
            int y = point % 1000;
            map[x][y] = -1;
            for (int[] ints : dir) {
                int px = x + ints[0], py = y + ints[1];
                if (px >= 0 && px < n && py >= 0 && py < m && heights[px][py] >= heights[x][y] && map[px][py] != -1) {
                    deque1.addLast(px * 1000 + py);
                    pointPacific.add(px * 1000 + py);
                }
            }
        }
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i][m - 1] = -1;
        }
        for (int i = 0; i < m; i++) {
            map[n - 1][i] = -1;
        }

        while (!deque2.isEmpty()) {
            Integer point = deque2.removeFirst();
            int x = point / 1000;
            int y = point % 1000;
            map[x][y] = -1;
            for (int[] ints : dir) {
                int px = x + ints[0], py = y + ints[1];
                if (px >= 0 && px < n && py >= 0 && py < m && heights[px][py] >= heights[x][y] && map[px][py] != -1) {
                    deque2.addLast(px * 1000 + py);
                    pointAtlantic.add(px * 1000 + py);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        Iterator<Integer> it = pointAtlantic.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            if (pointPacific.contains(next)) {
                ans.add(Arrays.asList(new Integer[]{next / 1000, next % 1000}));
            }
        }
        return ans;
    }

//    public static void dfs(int[][] map, int i, int j) {
//        int n = map.length;
//        int m = map[0].length;
//        if (i >= n || i < 0 || j >= m || j < 0 || map[i][j] == -1) return;
//        map[i][j] = -1;
//
//        if (i - 1 >= 0 && map[i - 1][j] != -1 && map[i - 1][j] <= map[i][j])
//            dfs(map, i - 1, j);
//        if (i + 1 < n && map[i + 1][j] != -1 && map[i + 1][j] <= map[i][j])
//            dfs(map, i + 1, j);
//        if (j - 1 >= 0 && map[i][j - 1] != -1 && map[i][j - 1] <= map[i][j])
//            dfs(map, i, j - 1);
//        if (j + 1 < m && map[i][j + 1] != -1 && map[i][j + 1] <= map[i][j])
//            dfs(map, i, j + 1);
//    }
}
