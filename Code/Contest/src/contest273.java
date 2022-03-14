import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class contest273 {
    public static void main(String[] args) {
//        System.out.println(isSameAfterReversals(0));
//        System.out.println(executeInstructions(3, new int[]{0, 1}, "RRDDLU"));
        System.out.println(getDistances(new int[]{2, 1, 3, 1, 2, 3, 3}));
    }

    public static boolean isSameAfterReversals(int num) {
        StringBuilder str = new StringBuilder(String.valueOf(num));
        int reverse = Integer.valueOf(str.reverse().toString());
        StringBuilder ans = new StringBuilder(String.valueOf(reverse));
        if (num == Integer.valueOf(ans.reverse().toString())) return true;
        return false;
    }

    public static int[] executeInstructions(int n, int[] startPos, String s) {
        HashMap<Character, int[]> dir = new HashMap<>();
        dir.put('R', new int[]{0, 1});
        dir.put('L', new int[]{0, -1});
        dir.put('D', new int[]{1, 0});
        dir.put('U', new int[]{-1, 0});
        int[] ans = new int[s.length()];
        char[] order = s.toCharArray();
        for (int i = 0; i < order.length; i++) {
            int starti = startPos[0], startj = startPos[1];
            int j = i;
            while (j < order.length) {
                int[] delta = dir.get(order[j]);

                int x = starti + delta[0];
                int y = startj + delta[1];
                if (x < 0 || x >= n || y < 0 || y >= n) break;
                j++;
                starti = x;
                startj = y;
            }
            ans[i] = j - i;
        }
        return ans;
    }

    public static long[] getDistances(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> list;
            if (map.containsKey(arr[i])) list = map.get(arr[i]);
            else list = new ArrayList<>();
            list.add(i);
            if (!map.containsKey(arr[i])) map.put(arr[i], list);
        }
        HashMap<Integer, Long> total = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Integer>> en : map.entrySet()) {
            ArrayList<Integer> list = en.getValue();
            long dis = 0;
            for (int i = 1; i < list.size(); i++) {
                dis += list.get(i) - list.get(i - 1);
            }
            total.put(en.getKey(), dis);
        }


        long[] ans = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> list;
            if (map.containsKey(arr[i])) list = map.get(arr[i]);
            else {
                ans[i] = 0;
                continue;
            }
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int idx = it.next();
                if (i == idx) continue;
                ans[i] += Math.abs(i - idx);
            }
        }
        return ans;
    }
}
