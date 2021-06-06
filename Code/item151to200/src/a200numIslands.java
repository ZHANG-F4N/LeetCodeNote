import java.util.ArrayDeque;
import java.util.Deque;

public class a200numIslands {
    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };


        System.out.println(numIslandsDFS(grid));
    }

    //DFS | BFS
    //BFS
    public static int numIslands(char[][] grid) {
        int ans = 0;
        int col = grid[0].length;
        int row = grid.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    grid[i][j] = '0';
                    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
                    arrayDeque.offer(i * col + j);
                    while (!arrayDeque.isEmpty()) {
                        int temp = arrayDeque.poll();
                        int iTemp = temp / col;
                        int jTemp = temp % col;

                        if (iTemp - 1 >= 0 && grid[iTemp - 1][jTemp] == '1') {
                            arrayDeque.offer((iTemp - 1) * col + jTemp);
                            grid[iTemp - 1][jTemp] = '0';
                        }
                        if (iTemp + 1 < row && grid[iTemp + 1][jTemp] == '1') {
                            arrayDeque.offer((iTemp + 1) * col + jTemp);
                            grid[iTemp + 1][jTemp] = '0';
                        }
                        if (jTemp - 1 >= 0 && grid[iTemp][jTemp - 1] == '1') {
                            arrayDeque.offer(iTemp * col + jTemp - 1);
                            grid[iTemp][jTemp - 1] = '0';
                        }
                        if (jTemp + 1 < col && grid[iTemp][jTemp + 1] == '1') {
                            arrayDeque.offer(iTemp * col + jTemp + 1);
                            grid[iTemp][jTemp + 1] = '0';
                        }

                    }
                }
            }
        }
        return ans;
    }

    //DFS
    public static int numIslandsDFS(char[][] grid) {
        int ans = 0;
        int col = grid[0].length;
        int row = grid.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void DFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            grid[i - 1][j] = '0';
            DFS(grid, i - 1, j);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            grid[i + 1][j] = '0';
            DFS(grid, i + 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            grid[i][j - 1] = '0';
            DFS(grid, i, j - 1);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            grid[i][j + 1] = '0';
            DFS(grid, i, j + 1);
        }
    }
}
