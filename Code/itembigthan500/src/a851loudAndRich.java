import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class a851loudAndRich {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(loudAndRich(new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3},
                {5, 3}, {6, 3}}, new int[]{3, 2, 5, 4, 6, 1, 7, 0})));
    }


    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] map = new int[n][n];
        int[] in = new int[n];

        for (int i = 0; i < richer.length; i++) {
            in[richer[i][1]]++;
            map[richer[i][0]][richer[i][1]] = 1;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
            if (in[i] == 0) deque.add(i);
        }

        while (!deque.isEmpty()) {
            int beg = deque.pollFirst();
            for (int i = 0; i < n; i++) {
                if (map[beg][i] == 1) {
                    // 入度为0 就是当前最富有的人 , 更新 比他穷的人
                    if (quiet[ans[beg]] < quiet[ans[i]]) ans[i] = ans[beg];
                    if (--in[i] == 0) deque.addLast(i);
                }
            }

        }
        return ans;
    }

    public static int max;
    public static int maxPerson;

    public static int[] loudAndRich2(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[][] map = new int[n][n];

        for (int i = 0; i < richer.length; i++) {
            map[richer[i][1]][richer[i][0]] = 1;
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            DFS(map, i, ans, quiet);
        }
        return ans;
    }

    public static void DFS(int[][] map, int i, int[] ans, int[] quiet) {
        for (int k = 0; k < map[i].length; k++) {

            DFS(map, k, ans, quiet);
        }
    }
}
