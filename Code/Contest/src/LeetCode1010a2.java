import java.util.Arrays;

public class LeetCode1010a2 {
    public static void main(String[] args) {

        int[][] grid = {{1, 2}, {3, 4}};
        System.out.println(minOperations(grid, 2));
    }

    public static int minOperations(int[][] grid, int x) {
        int Max = grid[0][0];
        int Min = grid[0][0];
        int M = grid.length;
        int N = grid[0].length;
        int ans = 0;
        int[] arr = new int[M * N];
        int index = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[index++] = grid[i][j];
//                if (grid[i][j] > Max) {
//                    Max = grid[i][j];
//                }
//                if (grid[i][j] < Min) {
//                    Min = grid[i][j];
//                }
            }
        }
        //int mid = (Max + Min) >> 1;
        Arrays.sort(arr);
        int mid = (M * N) >> 1;
        int tar = arr[mid];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int temp = Math.abs(grid[i][j] - tar);
                if (temp % x != 0) {
                    return -1;
                }
                ans += temp / x;
            }
        }
        return ans;
    }
}
