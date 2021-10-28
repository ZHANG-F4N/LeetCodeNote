import java.util.ArrayDeque;
import java.util.Deque;

public class a40maximalRectangle {
    public static void main(String[] args) {
        String[] matrix = {"10100", "10111", "11111", "10010"};
//        char[][] matrix2 = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}
        };


        System.out.println(maximalRectangle(matrix));
        System.out.println(maximalRectangle2(matrix2));
    }

    public static int maximalRectangle(String[] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length();
        int[][] preSum = new int[N][M + 2];

        for (int j = 1; j <= M; j++) {
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    preSum[i][j] = matrix[i].charAt(j - 1) - '0';
                } else if (matrix[i].charAt(j - 1) != '0') {
                    preSum[i][j] = preSum[i - 1][j] + matrix[i].charAt(j - 1) - '0';
                }
            }
        }

        //单调栈
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0;
        for (int j = 0; j < N; j++) {
            int[] temp = preSum[j];
            for (int i = 0; i < M + 2; i++) {
                while (!deque.isEmpty() && temp[deque.peek()] > temp[i]) {
                    int cur = deque.pop();
                    int area = (i - deque.peek() - 1) * (temp[cur]);
                    ans = area > ans ? area : ans;
                }
                deque.push(i);
            }
        }
        return ans;

    }

    public static int maximalRectangle2(char[][] matrix) {
        int N = matrix.length;
        if (N == 0) {
            return 0;
        }
        int M = matrix[0].length;
        int[][] preSum = new int[N][M + 2];
        for (int j = 1; j <= M; j++) {
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    preSum[i][j] = matrix[i][j - 1] - '0';
                } else if (matrix[i][j - 1] != '0') {
                    preSum[i][j] = preSum[i - 1][j] + matrix[i][j - 1] - '0';
                }
            }
        }
        //单调栈
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = 0;
        for (int j = 0; j < N; j++) {
            int[] temp = preSum[j];
            for (int i = 0; i < M + 2; i++) {
                while (!deque.isEmpty() && temp[deque.peek()] > temp[i]) {
                    int cur = deque.pop();
                    int area = (i - deque.peek() - 1) * (temp[cur]);
                    ans = area > ans ? area : ans;
                }
                deque.push(i);
            }
        }
        return ans;
    }
}
