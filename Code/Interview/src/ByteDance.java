import java.util.Scanner;

public class ByteDance {
    public static void main(String[] args) {

        Q3();
    }

        /*
    *
5 5
.....
.RRD.
.U.DR
.ULL.
....O

    * */


    public static char[][] map;
    public static boolean[][] visited;
    public static int[][] reachable;

    // Q2
    public static void Q2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        map = new char[n][m];
        visited = new boolean[n][m];
        reachable = new int[n][m];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            map[i] = line.toCharArray();

        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 0 未处理过 1 不可达 2 可达
                if (reachable[i][j] == 0) dfs(i, j);
                if (reachable[i][j] == 2) ans++;
            }
        }
        System.out.println(n * m - ans);
    }

    public static boolean dfs(int i, int j) {
        if (i >= map.length || j >= map[0].length || i < 0 || j < 0 || visited[i][j]) return false;

        if (reachable[i][j] == 2) return true;
        if (reachable[i][j] == 1) return false;

        boolean ret = false;
        visited[i][j] = true;
        if (map[i][j] == 'O') {
            reachable[i][j] = 2;
            visited[i][j] = false;
            return true;
        } else if (map[i][j] == '.') {
            ret = dfs(i + 1, j) || dfs(i - 1, j) || dfs(i, j + 1) || dfs(i, j - 1);
        } else if (map[i][j] == 'U') {
            ret = dfs(i - 1, j);
        } else if (map[i][j] == 'D') {
            ret = dfs(i + 1, j);
        } else if (map[i][j] == 'L') {
            ret = dfs(i, j - 1);
        } else if (map[i][j] == 'R') {
            ret = dfs(i, j + 1);
        }
        visited[i][j] = false;
        if (ret) reachable[i][j] = 2;
        else reachable[i][j] = 1;
        return ret;
    }

    public static void Q3() {
        for (int i = 0; i < 80; i++) {
            System.out.println("[\"" + i + "\"" + ",],");
        }
    }

}
