import java.util.Arrays;
import java.util.HashMap;

public class contest282 {
    public static void main(String[] args) {
//        System.out.println(prefixCount(new String[]{"pay", "attention", "practice", "attend"}, "at"));

//        System.out.println(minSteps("leetcode", "coats"));
//        System.out.println(minimumTime(new int[]{1, 2, 3}, 5));
        System.out.println(minimumFinishTime(new int[][]{{2, 3}, {3, 4}}, 5, 4));
//        System.out.println(minimumTime(new int[]{2 }, 1));
    }


    //Q1
    public static int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            if (word.startsWith(pref)) ans++;
        }
        return ans;
    }

    //Q2
    public static int minSteps(String s, String t) {
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (char c : s.toCharArray()) {
            map1[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map2[c - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            int max = Math.max(map1[i], map2[i]);
            ans += 2 * max - map1[i] - map2[i];
        }
        return ans;
    }

    //Q3
    public static long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);
        int min = time[0];
        long maxTime = (long) min * (long) totalTrips;
        long l = 0, r = maxTime;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            long trip = 0;
            for (int i : time) {
                trip += mid / i;
                if (mid / i == 0) break;
            }
            if (trip >= totalTrips) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //Q4
    public static int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int[][] dp = new int[tires.length][numLaps];
        int num = tires.length;
        int preT = -1;
        int preL = 1;
        int preMin = Integer.MAX_VALUE;
        for (int i = 0; i < num; i++) {
            dp[i][0] = tires[i][1];
            if (preMin > dp[i][0]) {
                preT = i;
                preMin = dp[i][0];
            }
        }

        for (int j = 1; j < numLaps; j++) {
            for (int i = 0; i < num; i++) {
                if (preT == i) {
                    int change = preMin + changeTime + tires[i][0];
                    int unchange = preMin + tires[i][0] * (int) Math.pow(tires[i][1], preL - 1);
//                    dp[i][j] = Math.min();
                } else {
                    dp[i][j] = preMin + changeTime + tires[i][0];
                }
            }
        }
        return 0;

    }
}
