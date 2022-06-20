import java.util.*;

public class contest287 {
    public static void main(String[] args) {
//        System.out.println(convertTime("02:30", "04:35"));
        System.out.println(maximumCandies(new int[]{5, 8, 6}, 3));

    }

    public static int convertTime(String current, String correct) {

        String[] time = current.split(":");
        int curH = Integer.parseInt(time[0]);
        int curM = Integer.parseInt(time[1]);
        time = correct.split(":");
        int corH = Integer.parseInt(time[0]);
        int corM = Integer.parseInt(time[1]);

        int curVal = curH * 60 + curM;
        int corVal = corH * 60 + corM;
        int minus = corVal - curVal;

        int ans = 0;
        ans += minus / 60;
        minus %= 60;
        ans += minus / 15;
        minus %= 15;
        ans += minus / 5;
        minus %= 5;
        ans += minus;
        return ans;
    }

    public static List<List<Integer>> findWinners(int[][] matches) {
        HashMap<Integer, Integer> mapWin = new HashMap<>();
        HashMap<Integer, Integer> mapLoss = new HashMap<>();
        for (int[] match : matches) {
            int win = match[0];
            int loss = match[1];
            mapWin.put(win, mapWin.getOrDefault(win, 0) + 1);
            mapLoss.put(loss, mapLoss.getOrDefault(loss, 0) + 1);
        }

        List<Integer> allWin = new ArrayList<>();
        List<Integer> lossOne = new ArrayList<>();
        for (Map.Entry<Integer, Integer> en : mapWin.entrySet()) {
            if (!mapLoss.containsKey(en.getKey())) allWin.add(en.getKey());
        }
        for (Map.Entry<Integer, Integer> entry : mapLoss.entrySet()) {
            if (entry.getValue() == 1) lossOne.add(entry.getKey());
        }
        Collections.sort(allWin);
        Collections.sort(lossOne);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(allWin);
        ans.add(lossOne);
        return ans;

    }

    public static int maximumCandies(int[] candies, long k) {
        long sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
//        long sum = Arrays.stream(candies).sum();// 可能有问题
        if (sum < k) return 0;
        // 二分
        int l = 1, r = (int) (sum / k);
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(candies, mid, k)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static boolean check(int[] candies, int val, long k) {
        // 能否分成k个val
        long num = 0;
        for (int i = 0; i < candies.length; i++) {
            if (num >= k) return true;
            if (candies[i] >= val) num += candies[i] / val;
        }

        return num >= k;
    }


}
