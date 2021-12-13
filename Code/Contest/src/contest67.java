import java.util.*;

public class contest67 {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(maxSubsequence(new int[]{-1, -2, 3, 4}, 3)));
//        System.out.println(goodDaysToRobBank(new int[]{1}, 2).toString());
//        System.out.println(goodDaysToRobBank(new int[]{1, 2, 3, 4, 5, 6}, 2).toString());
//        System.out.println(goodDaysToRobBank(new int[]{5, 3, 3, 3, 5, 6, 2}, 2).toString());

        System.out.println(maximumDetonation(new int[][]{{54, 95, 4}, {99, 46, 3}, {29, 21, 3}, {96, 72, 8}, {49, 43, 3}, {11, 20, 3}, {2, 57, 1}, {69, 51, 7}, {97, 1, 10}, {85, 45, 2}, {38, 47, 1}, {83, 75, 3}, {65, 59, 3}, {33, 4, 1}, {32, 10, 2}, {20, 97, 8}, {35, 37, 3}}));
    }

    public static int[] maxSubsequence(int[] nums, int k) {

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = nums[i];
        }
        for (int i = k; i < nums.length; i++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < k; j++) {
                if (min > ans[j]) {
                    min = ans[j];
                    idx = j;
                }
            }
            if (nums[i] > min) {
                for (int j = idx; j < k - 1; j++) {
                    ans[j] = ans[j + 1];
                }
                ans[k - 1] = nums[i];
            }

        }
        return ans;

    }

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> ans = new ArrayList<>();
        if (time == 0) {
            for (int i = 0; i < security.length; i++) {
                ans.add(i);
            }
            return ans;
        }
        int n = security.length;
        int[] down = new int[n];
        int[] up = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i - 1] >= security[i]) {
                down[i] = down[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                up[i] = up[i + 1] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i < time) continue;
            if (i >= n - time) continue;
            if (up[i] >= time && down[i] >= time) ans.add(i);
        }
        return ans;
    }


    public static int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        int[][] map = new int[n][n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (judge(bombs[i], bombs[j])) {
                    map[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int temp = 1;
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) deque.addLast(j);
            }
            while (!deque.isEmpty()) {
                int k = deque.pollFirst();
                temp++;
                for (int j = 0; j < n; j++) {
                    if (map[k][j] == 1 ) deque.addLast(j);
                }
            }
            max = Math.max(temp, max);
        }


        return max;
    }

    public static void DFS(int[][] map, int i, int j) {


    }

    public static boolean judge(int[] bomb1, int[] bomb2) {

        long dis = Math.abs((long) bomb1[0] - (long) bomb2[0]) * Math.abs((long) bomb1[0] - (long) bomb2[0]) +
                Math.abs((long) bomb1[1] - (long) bomb2[1]) * Math.abs((long) bomb1[1] - (long) bomb2[1]);

        return dis <= ((long) bomb1[2] * (long) bomb1[2]);
    }
}
