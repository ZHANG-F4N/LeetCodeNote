import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HuaWei062302 {

    public static int freshTomato = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = Integer.parseInt(scanner.next());
        int N = Integer.parseInt(scanner.next());
        char[][] tomatoGrid = new char[M][N];
        char[][] tomatoGridTemp = new char[M][N];
        int[][] dayTomatoGrid = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tomatoGrid[i][j] = Character.toUpperCase(scanner.next().charAt(0));
                tomatoGridTemp[i][j] = tomatoGrid[i][j];
                dayTomatoGrid[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (tomatoGrid[i][j] == 'B') {
                    DFS(tomatoGrid, dayTomatoGrid, i, j, 0);
//                    for (int k = 0; k < tomatoGrid.length; k++) {
//                        tomatoGrid[k] = tomatoGridTemp[k].clone();
//                    }
                }
            }
        }
    }

    public static void DFS(char[][] tomatoGrid, int[][] dayTomatoGrid, int i, int j, int day) {
        if (i >= tomatoGrid.length || j >= tomatoGrid[0].length || i < 0 || j < 0) {
            return;
        }
        if (tomatoGrid[i][j] == 'E') {
            return;
        }

        if (i + 1 < tomatoGrid.length && tomatoGrid[i + 1][j] == 'R') {
            tomatoGrid[i + 1][j] = 'B';
            dayTomatoGrid[i + 1][j] = Math.min(day + 1, dayTomatoGrid[i + 1][j]);
            DFS(tomatoGrid, dayTomatoGrid, i + 1, j, day + 1);
            tomatoGrid[i + 1][j] = 'R';
        }
        if (i - 1 >= 0 && tomatoGrid[i - 1][j] == 'R') {
            tomatoGrid[i - 1][j] = 'B';
            dayTomatoGrid[i - 1][j] = Math.min(day + 1, dayTomatoGrid[i - 1][j]);
            DFS(tomatoGrid, dayTomatoGrid, i - 1, j, day + 1);
            tomatoGrid[i - 1][j] = 'R';
        }
        if (j + 1 < tomatoGrid[0].length && tomatoGrid[i][j + 1] == 'R') {
            tomatoGrid[i][j + 1] = 'B';
            dayTomatoGrid[i][j + 1] = Math.min(day + 1, dayTomatoGrid[i][j + 1]);
            DFS(tomatoGrid, dayTomatoGrid, i, j + 1, day + 1);
            tomatoGrid[i][j + 1] = 'R';
        }
        if (j - 1 >= 0 && tomatoGrid[i][j - 1] == 'R') {
            tomatoGrid[i][j - 1] = 'B';
            dayTomatoGrid[i][j - 1] = Math.min(day + 1, dayTomatoGrid[i][j - 1]);
            DFS(tomatoGrid, dayTomatoGrid, i, j - 1, day + 1);
            tomatoGrid[i][j - 1] = 'R';
        }

        return;
    }

}
