import javax.management.MBeanRegistration;
import javax.print.DocFlavor;
import java.util.*;

public class contest295 {

    public static void main(String[] args) {
        System.out.println(minimumObstacles(new int[][]{{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}}));
        System.out.println(minimumObstacles(new int[][]{{0, 1, 1}, {1, 1, 0}, {1, 1, 0}}));
//        System.out.println(rearrangeCharacters("ilovecodingonleetcode", "code"));
//        System.out.println(totalSteps(new int[]{10, 1, 2, 3, 4, 5, 6, 1, 2, 3}));
//        System.out.println(totalSteps(new int[]{4, 5, 7, 7, 13}));
//        System.out.println(totalSteps(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}));
//        System.out.println(discountPrices("$7383692 5q $", 64));
//        System.out.println(discountPrices("1 2 $3 4 $5 $6 7 8$ $9 $10$", 100));
//        System.out.println(discountPrices("there are $1 $2 and 5$ candies in the shop", 50));
    }

    //Q1
    public static int rearrangeCharacters(String s, String target) {
        int[] map = new int[26];
        int[] tmap = new int[26];

        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : target.toCharArray()) {
            tmap[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (tmap[i] != 0) {
                ans = Math.min(map[i] / tmap[i], ans);
            }
        }

        return ans;
    }


    //Q2
    public static String discountPrices(String sentence, int discount) {
        String[] word = sentence.split(" ");
        StringBuilder ans = new StringBuilder();
        for (String s : word) {
            if (s.charAt(0) == '$') {
                if (s.equals("$")) {
                    ans.append(s);
                    ans.append(" ");
                    continue;
                }
                boolean f = true;
                HashMap<Character, Integer> map = new HashMap<>();
                for (char c : s.toCharArray()) {
                    map.put(c, map.getOrDefault(c, 0) + 1);
                }
                for (Map.Entry<Character, Integer> en : map.entrySet()) {
                    if (!((en.getKey() <= '9' && en.getKey() >= '0') || en.getKey() == '.' || en.getKey() == '$')) {
                        f = false;
                        break;
                    }
                    if (en.getKey() == '.' && en.getValue() >= 2) {
                        f = false;
                        break;
                    }
                    if (en.getKey() == '$' && en.getValue() >= 2) {
                        f = false;
                        break;
                    }
                }
                if (f) {
                    double val = Double.parseDouble(s.substring(1));
                    val = (val * (100 - discount) / 100.0);
                    s = "$" + String.format("%.2f", val);
                }
            }
            ans.append(s);
            ans.append(" ");
        }

        return ans.substring(0, ans.length() - 1);


    }

    //Q3
    public static int totalSteps(int[] nums) {
        int l = 0, r = 0, n = nums.length;
        int ans = 0;
        while (r < n) {
            if (nums[r] >= nums[l]) {
                ans = Math.max(ans, r - l - 1);
                l = r;
            }
            r++;
        }
        ans = Math.max(ans, r - l - 1);
        return ans;
    }

    //Q4
    public static int ans;

    public static int minimumObstacles(int[][] grid) {
//        ans = Integer.MAX_VALUE;
//        dfs(grid, 0, 0, 0);
//        return ans;
        int n = grid.length;
        int m = grid[0].length;
        var dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        var cost = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        cost[0][0] = 0;
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});
        while (!deque.isEmpty()) {
            var q = deque.pollFirst();
            int x = q[0], y = q[1];
            for (int[] d : dir) {
                int dx = x + d[0], dy = y + d[1];
                if (dx < n && dx >= 0 && dy >= 0 && dy < m) {
                    int val = grid[dx][dy];
                    if (val + cost[x][y] < cost[dx][dy]) {
                        cost[dx][dy] = val + cost[x][y];
                        if (val == 0) deque.addFirst(new int[]{dx, dy});
                        else deque.addLast(new int[]{dx, dy});
                    }

                }
            }

        }
        return cost[n - 1][m - 1];
    }

    public static void dfs(int[][] grid, int i, int j, int obstacle) {
        int n = grid.length;
        int m = grid[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || obstacle > ans) return;
        if (i == n - 1 && j == m - 1) {
            ans = Math.min(ans, obstacle);
            return;
        }
        int temp = grid[i][j];
        grid[i][j] = -1;
        if (i + 1 < n && grid[i + 1][j] != -1) dfs(grid, i + 1, j, obstacle + temp);
        if (i - 1 >= 0 && grid[i - 1][j] != -1) dfs(grid, i - 1, j, obstacle + temp);
        if (j + 1 < m && grid[i][j + 1] != -1) dfs(grid, i, j + 1, obstacle + temp);
        if (j - 1 >= 0 && grid[i][j - 1] != -1) dfs(grid, i, j - 1, obstacle + temp);
        grid[i][j] = temp;
    }


}
