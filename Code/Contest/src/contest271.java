import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class contest271 {
    public static void main(String[] args) {
//        System.out.println(countPoints("B0B6G0R6R0R6G9"));
        System.out.println(subArrayRanges(new int[]{4, -2, -3, 4, 1}));
    }

    public static int countPoints(String rings) {
        char[] arr = rings.toCharArray();
        ArrayList[] array = new ArrayList[10];
        for (int i = 0; i < arr.length; i += 2) {
            int idx = arr[i + 1] - '0';
            if (array[idx] == null) array[idx] = new ArrayList<Character>();
            array[idx].add(arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            if (array[i] != null && array[i].lastIndexOf('R') != -1 && array[i].lastIndexOf('G') != -1 &&
                    array[i].lastIndexOf('B') != -1) {
                ans++;
            }
        }
        return ans;
    }

    public static long subArrayRanges(int[] nums) {
        long ans = 0;
        long[] max = new long[nums.length];
        long[] min = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max[i] = nums[i];
            min[i] = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                max[j] = Math.max(max[j - 1], nums[j]);
                min[j] = Math.min(min[j - 1], nums[j]);
            }
            for (int j = i; j < nums.length; j++) {
                ans += max[j] - min[j];
            }
        }
        return ans;
    }

    public static int minimumRefill(int[] plants, int capacityA, int capacityB) {
//        int[] pre = new int[plants.length + 1];
//        int[] late = new int[plants.length + 1];
//        for (int i = 1; i < plants.length; i++) {
//            pre[i] = pre[i - 1] + plants[i - 1];
//        }
//        for (int i = plants.length - 2; i >= 0; i--) {
//            late[i] = late[i + 1] + plants[i + 1];
//        }
        int i = 0;
        int j = plants.length - 1;
        int ARest = capacityA;
        int BRest = capacityB;
        int ans = 0;
        while (i < j) {
            if (ARest >= plants[i] && BRest >= plants[j]) {
                ARest -= plants[i];
                i++;
                BRest -= plants[j];
                j--;
            } else if (ARest < plants[i] && BRest < plants[j]) {
                ans += 2;
                ARest = capacityA;
                BRest = capacityB;
            } else if (ARest < plants[i]) {
                ans++;
                ARest = capacityA;
            } else if (BRest < plants[j]) {
                ans++;
                BRest = capacityB;
            }
        }
        if (i == j) {
            if (ARest == BRest) {
                if (ARest < plants[i]) ans++;
            } else if (Math.max(ARest, BRest) < plants[i]) {
                ans++;
            }
        }
        return ans;
    }

    public static int tAns = 0;
    public static int ans = 0;

    public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int[] pos = new int[fruits.length];
        for (int i = 0; i < fruits.length; i++) {
            pos[i] = fruits[i][0];
        }
        DFS(fruits, pos, startPos, k, -1);
        DFS(fruits, pos, startPos, k, 1);
        return ans;
    }

    public static void DFS(int[][] fruits, int[] pos, int startPos, int k, int dir) {
        if (k <= 0) {
            ans = Math.max(tAns, ans);
            return;
        }
        for (int j = 0; j < pos.length; j++) {
            if (pos[j] == startPos) {
                tAns += fruits[startPos][1];
                int temp = pos[j];
                pos[j] = -1;
                DFS(fruits, pos, startPos, k, -1);
                DFS(fruits, pos, startPos, k, 1);
                pos[j] = temp;
                return;
            }
        }
        for (int i = 0; i < pos.length; i++) {
            
        }

        

    }
}
