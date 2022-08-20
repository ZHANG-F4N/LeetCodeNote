import java.lang.reflect.Array;
import java.util.*;

public class contest305 {
    public static void main(String[] args) {
        System.out.println(longestIdealString("acfgbd", 2));
        System.out.println(validPartition(new int[]{579611, 579611, 579611, 731172, 731172, 496074, 496074, 496074, 151416, 151416, 151416}));
        System.out.println(validPartition(new int[]{1, 1, 1, 2}));
        System.out.println(validPartition(new int[]{4, 4, 4, 5, 6}));

        int[][] t = new int[][]{{0, 1}, {1, 2}, {3, 1}, {4, 0}, {0, 5}, {5, 6}};
        System.out.println(reachableNodes(7, t, new int[]{4, 5}));
    }

    //Q1
    public static int arithmeticTriplets(int[] nums, int diff) {
        Arrays.sort(nums);

        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }


    public static int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : restricted) {
            set.add(i);
        }
        List[] map = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            map[edges[i][0]].add(edges[i][1]);
            map[edges[i][1]].add(edges[i][0]);
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        int ans = 1;
        int[] visit = new int[n];
        visit[0] = 1;
        while (!deque.isEmpty()) {
            int val = deque.pollFirst();
            List<Integer> leaf = map[val];
            if (set.contains(val)) continue;
            for (int i : leaf) {
                if (visit[i] != 1 && !set.contains(i)) {
                    ans++;
                    visit[i] = 1;
                    deque.addLast(i);
                }
            }

        }
        return ans;


    }


    public static boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        dp[1] = false;
        dp[2] = nums[0] == nums[1];

        for (int i = 2; i < n; i++) {
            int r = nums[i], m = nums[i - 1], l = nums[i - 2];
            if (dp[i - 1] && r == m) dp[i + 1] = true;
            if (dp[i - 2] && r == m && m == l) dp[i + 1] = true;
            if (dp[i - 2] && r - m == 1 && m - l == 1) dp[i + 1] = true;
        }


        return dp[n];

    }

    public static int longestIdealString(String s, int k) {
        if (s.length() == 1) return 1;
        int[] diff = new int[s.length() - 1];
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            diff[i - 1] = chars[i] - chars[i - 1];
        }
        int len = 1;


        return len;


    }
}
