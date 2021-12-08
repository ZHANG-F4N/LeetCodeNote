import java.awt.event.MouseAdapter;
import java.lang.ref.SoftReference;

public class LCS3largestArea {
    public static void main(String[] args) {
        System.out.println(largestArea(new String[]{"000", "010", "000"}));
        System.out.println(largestArea(new String[]{"110", "231", "221"}));
        System.out.println(largestArea(new String[]{"11111100000", "21243101111", "21224101221", "11111101111"}));
    }

    public static int ans;

    public static int largestArea(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = grid[i].toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] == '0') {
                    map[i][j] = '#';
                    if (i + 1 < map.length) DFS(map, i + 1, j, map[i + 1][j]);
                    if (i - 1 >= 0) DFS(map, i - 1, j, map[i - 1][j]);
                    if (j + 1 < map[0].length) DFS(map, i, j + 1, map[i][j + 1]);
                    if (j - 1 >= 0) DFS(map, i, j - 1, map[i][j - 1]);
                } else if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    char ch = map[i][j];
                    map[i][j] = '#';
                    if (i + 1 < map.length) DFS(map, i + 1, j, ch);
                    if (i - 1 >= 0) DFS(map, i - 1, j, ch);
                    if (j + 1 < map[0].length) DFS(map, i, j + 1, ch);
                    if (j - 1 >= 0) DFS(map, i, j - 1, ch);
                }
            }
        }

        ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (map[i][j] != '#') {
                    ans = Math.max(count(map, i, j, map[i][j]), ans);
                }
            }
        }
        return ans;
    }

    public static void DFS(char[][] map, int i, int j, char num) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] == '#') {
            return;
        }
        if (map[i][j] == '0') {
            map[i][j] = '#';
            if (i + 1 < map.length) DFS(map, i + 1, j, map[i + 1][j]);
            if (i - 1 >= 0) DFS(map, i - 1, j, map[i - 1][j]);
            if (j + 1 < map[0].length) DFS(map, i, j + 1, map[i][j + 1]);
            if (j - 1 >= 0) DFS(map, i, j - 1, map[i][j - 1]);
        }else if (map[i][j] == num){
            map[i][j] = '#';
            if (i + 1 < map.length) DFS(map, i + 1, j, num);
            if (i - 1 >= 0) DFS(map, i - 1, j, num);
            if (j + 1 < map[0].length) DFS(map, i, j + 1, num);
            if (j - 1 >= 0) DFS(map, i, j - 1, num);
        }
    }

    public static int count(char[][] map, int i, int j, char num) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length || map[i][j] != num) {
            return 0;
        }
        map[i][j] = '#';
        int area = 1;
        if (i + 1 < map.length) area += count(map, i + 1, j, num);
        if (i - 1 >= 0) area += count(map, i - 1, j, num);
        if (j + 1 < map[0].length) area += count(map, i, j + 1, num);
        if (j - 1 >= 0) area += count(map, i, j - 1, num);
        return area;
    }
}
