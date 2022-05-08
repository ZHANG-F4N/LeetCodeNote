import java.util.ArrayList;
import java.util.List;

public class LeetCup2022spring {
    public static void main(String[] args) {
//        System.out.println(getMinimumTime(new int[]{2, 3, 2}, new int[][]{{0, 2}, {1, 4}, {2, 1}}, 3));
        System.out.println(conveyorBelt(new String[]{">^^>", "<^v>", "^v^<"}, new int[]{0, 0},
                new int[]{1, 3}));

        System.out.println(conveyorBelt(new String[]{">>v", "v^<", "<><"}, new int[]{0, 1}, new int[]{2,
                0}));
        System.out.println(conveyorBelt(new String[]{">>v", ">>v", "^<<"}, new int[]{0, 0},
                new int[]{1, 1}));


    }


    public static int getMinimumTime(int[] time, int[][] fruits, int limit) {
        int ans = 0;
        int n = fruits.length;
        for (int i = 0; i < n; i++) {
            int type = fruits[i][0];
            int num = fruits[i][1];
            if (num % limit == 0) ans += time[type] * (num / limit);
            else ans += time[type] * (num / limit + 1);
        }

        return ans;
    }


    public static int op;
    public static int ans;
    public static List<Character> real;
    public static List<Character> mark;

    public static int conveyorBelt(String[] matrix, int[] start, int[] end) {
        int n = matrix.length;
        int m = matrix[0].length();
        char[][] map = new char[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = matrix[i].charAt(j);
            }
        }
        op = Integer.MAX_VALUE;
//        change(map, start[0], start[1], end[0], end[1], 0);
//        test(map, start[0], start[1], end[0], end[1], 0);
        mark = new ArrayList<>();
        real = new ArrayList<>();
//        real.add(map[start[0]][start[1]]);
        dfs(map, start[0], start[1], end[0], end[1]);
        return op;
    }

    public static void dfs(char[][] map, int i, int j, int tarX, int tarY) {
        int n = map.length;
        int m = map[0].length;
        if (i >= n || i < 0 || j >= m || j < 0 || map[i][j] == '*') return;

        if (i == tarX & j == tarY) {
            int deep = mark.size();
            int temp = 0;
            for (int k = 0; k < deep; k++) {
                if (mark.get(k) != real.get(k)) temp++;
            }
            op = Math.min(op, temp);
            return;
        }
        mark.add(map[i][j]);
        char val = map[i][j];
        map[i][j] = '*';
        real.add('<');
        dfs(map, i, j - 1, tarX, tarY);
        real.remove(real.size() - 1);

        real.add('>');
        dfs(map, i, j + 1, tarX, tarY);
        real.remove(real.size() - 1);

        real.add('^');
        dfs(map, i - 1, j, tarX, tarY);
        real.remove(real.size() - 1);

        real.add('v');
        dfs(map, i + 1, j, tarX, tarY);
        real.remove(real.size() - 1);

        mark.remove(mark.size() - 1);

        map[i][j] = val;

    }

//    public static boolean test(char[][] map, int i, int j, int tarX, int tarY, int tempOP) {
//        int n = map.length;
//        int m = map[0].length;
//        if (i >= n || i < 0 || j >= m || j < 0 || map[i][j] == '*') return false;
//        if (i == tarX & j == tarY) {
//            op = Math.min(tempOP, op);
//            return true;
//        }
//        char val = map[i][j];
//        boolean ar = false;
//        map[i][j] = '*';
//        if (val == '<') ar = ar || test(map, i, j - 1, tarX, tarY, tempOP);
//        if (val == '>') ar = ar || test(map, i, j + 1, tarX, tarY, tempOP);
//        if (val == '^') ar = ar || test(map, i - 1, j, tarX, tarY, tempOP);
//        if (val == 'v') ar = ar || test(map, i + 1, j, tarX, tarY, tempOP);
//        map[i][j] = val;
//        if (ar) {
//            op = Math.min(tempOP, op);
//            return true;
//        } else {
//            map[i][j] = '*';
//            test(map, i, j - 1, tarX, tarY, tempOP + 1);
//            test(map, i, j + 1, tarX, tarY, tempOP + 1);
//            test(map, i - 1, j, tarX, tarY, tempOP + 1);
//            test(map, i + 1, j, tarX, tarY, tempOP + 1);
////            map[i][j] = val;
//        }
//        return false;
//    }


//    public static void change(char[][] map, int i, int j, int tarX, int tarY, int tempOP) {
//        int n = map.length;
//        int m = map[0].length;
//        if (i >= n || i < 0 || j >= m || j < 0 || map[i][j] == '*')
//            return;
//        if (i == tarX && j == tarY) {
//            op = Math.min(op, tempOP);
//            return;
//        }
//        arrive = false;
//        dfs(map, i, j, tarX, tarY);
//        if (arrive) {
//            op = Math.min(op, tempOP);
//            return;
//        }
//        map[i][j] = '*';
//        change(map, i, j - 1, tarX, tarY, tempOP + 1);
//        change(map, i, j + 1, tarX, tarY, tempOP + 1);
//        change(map, i - 1, j, tarX, tarY, tempOP + 1);
//        change(map, i + 1, j, tarX, tarY, tempOP + 1);
//    }

    // * 访问过
//    public static void dfs(char[][] map, int i, int j, int tarX, int tarY) {
//        int n = map.length;
//        int m = map[0].length;
//        if (i >= n || i < 0 || j >= m || j < 0 || map[i][j] == '*') return;
//        if (i == tarX & j == tarY) {
//            arrive = true;
//            return;
//        }
//        char val = map[i][j];
//        map[i][j] = '*';
//        if (val == '<') dfs(map, i, j - 1, tarX, tarY);
//        if (val == '>') dfs(map, i, j + 1, tarX, tarY);
//        if (val == '^') dfs(map, i - 1, j, tarX, tarY);
//        if (val == 'v') dfs(map, i + 1, j, tarX, tarY);
//        map[i][j] = val;
//    }
}
