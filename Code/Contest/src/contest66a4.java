import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class contest66a4 {
    public static void main(String[] args) {
        System.out.println(countPyramids(new int[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 0, 0, 1}
        }));

    }

    public static int countPyramids(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        int temp = ans;
        // 暴力超时
        for (int h = 2; h <= n; h++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    // grid[i][j]   当顶
                    //r <= i <= r + h - 1
                    boolean flag = true;
                    for (int i1 = i; i1 <= i + h - 1; i1++) {
                        //c - (i - r) <= j <= c + (i - r)
                        for (int j1 = j - (i1 - i); j1 <= j + (i1 - i); j1++) {
                            if (i1 < 0 || i1 >= n || j1 < 0 || j1 >= m || grid[i1][j1] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        ans++;
                    }
                }
            }
            if (temp == ans) {
                break;
            }
            temp = ans;
        }
        temp = ans;
        for (int h = 2; h <= n; h++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    // grid[i][j]   当顶
                    boolean flag = true;
                    //r - h + 1 <= i <= r
                    for (int i1 = i - h + 1; i1 <= i; i1++) {
                        //c - (r - i) <= j <= c + (r - i)
                        for (int j1 = j - (i - i1); j1 <= j + (i - i1); j1++) {
                            if (i1 < 0 || i1 >= n || j1 < 0 || j1 >= m || grid[i1][j1] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        ans++;
                    }
                }
            }
            if (temp == ans) {
                break;
            }
            temp = ans;
        }

        return ans;


    }



}
