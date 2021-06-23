import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HuaWei6232 {

    public static int freshTomato = 0;
    public static int day = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = Integer.parseInt(scanner.next());
        int N = Integer.parseInt(scanner.next());
        char[][] tomatoGrid = new char[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                tomatoGrid[i][j] = Character.toUpperCase(scanner.next().charAt(0));
            }
        }
        int max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (tomatoGrid[i][j] =='B'){
                    DFS(tomatoGrid,i,j);
                }
            }
        }

    }
    public static void BFS(char[][] tomatoGrid, int i, int j){
        if (i >= tomatoGrid.length || j >= tomatoGrid[0].length || i < 0 || j < 0) {
            return;
        }
        if (tomatoGrid[i][j] == 'B'){
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        }

    }


    public static void DFS(char[][] tomatoGrid, int i, int j) {
        if (i >= tomatoGrid.length || j >= tomatoGrid[0].length || i < 0 || j < 0) {
            return;
        }
        if (tomatoGrid[i][j] == 'E') {
            return;
        }
        if (tomatoGrid[i][j] == 'B') {
            boolean flag = false;
            if (i + 1 < tomatoGrid.length && tomatoGrid[i + 1][j] == 'R') {
                flag = true;
                tomatoGrid[i + 1][j] = 'B';
                DFS(tomatoGrid, i + 1, j);

            }
            if (i - 1 >= 0 && tomatoGrid[i - 1][j] == 'R') {
                flag = true;
                tomatoGrid[i - 1][j] = 'B';
                DFS(tomatoGrid, i - 1, j);
            }
            if (j + 1 < tomatoGrid[0].length && tomatoGrid[i][j + 1] == 'R') {
                flag = true;
                tomatoGrid[i][j + 1] = 'B';
                DFS(tomatoGrid, i, j + 1);
            }
            if (j - 1 >= 0 && tomatoGrid[i][j - 1] == 'R') {
                flag = true;
                tomatoGrid[i][j - 1] = 'B';
                DFS(tomatoGrid, i, j - 1);
            }
            if (flag){
                day++;
            }
        }
        return;
    }

}
