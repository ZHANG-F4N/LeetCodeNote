import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetEase20220317 {
    public static void main(String[] args) {
        Q1();
    }


    public static int ans;
    public static int emp;
    public static int[][] ansMap;
    public static HashMap<Integer, int[]>[] constraint;

    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int p = 0; p < T; p++) {
            ans = 0;
            emp = 0;
            ansMap = new int[3][3];
            int[][] map = new int[3][3];
            for (int o = 0; o < 3; o++) {
                String line = scanner.next();
                if (line.charAt(0) != '*') map[o][0] =
                        Integer.parseInt(String.valueOf(line.charAt(0)));
                else emp++;
                if (line.charAt(1) != '*') map[o][1] =
                        Integer.parseInt(String.valueOf(line.charAt(1)));
                else emp++;
                if (line.charAt(2) != '*') map[o][2] =
                        Integer.parseInt(String.valueOf(line.charAt(2)));
                else emp++;
            }
            constraint = new HashMap[3];
            for (int i = 0; i < 3; i++) {
                constraint[i] = new HashMap<>();
                for (int j = 0; j < 3; j++) {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    constraint[i].put(j, new int[]{x, y});
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (map[i][j] == 0) {
                        backtrace(map, i, j, 0);
                    }
                }
            }
            if (ans == 0) System.out.println("No");
            else if (ans > 1) System.out.println("Multiple");
            else {
                System.out.println("Unique");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(ansMap[i][j]);
                    }
                    System.out.println();
                }
            }

        }
    }

    public static boolean check(int[][] map, int x, int y, int val) {
        int cell = -1;
        for (int i = 0; i < 3; i++) {
            if (map[x][i] == val) return false;
            if (map[i][y] == val) return false;
            for (Map.Entry<Integer, int[]> en : constraint[i].entrySet()) {
                if (en.getValue()[0] == x && en.getValue()[1] == y) {
                    cell = i;
                }
            }
        }
        for (Map.Entry<Integer, int[]> en : constraint[cell].entrySet()) {
            if (map[en.getValue()[0]][en.getValue()[1]] == val) return false;
        }
        return true;
    }

    public static void backtrace(int[][] map, int x, int y, int num) {
        for (int a = 0; a < 3; a++) {
            if (check(map, x, y, a + 1)) {
                map[x][y] = a + 1;
                if (num + 1 == emp) {
                    ans++;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            ansMap[i][j] = map[i][j];
                        }
                    }
                    return;
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (map[i][j] == 0) {
                            backtrace(map, i, j, num + 1);
                        }
                    }
                }
                map[x][y] = 0;
            }
        }
    }

}
