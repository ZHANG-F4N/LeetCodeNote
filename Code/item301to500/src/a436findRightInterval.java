import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class a436findRightInterval {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{1, 2}, {2, 3}, {0, 1}, {3, 4}})));
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{1, 4}, {2, 3}, {3, 4}})));
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
    }

    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        HashMap<Integer, Integer> hashMap = new HashMap<>();

//        HashMap<Integer, Integer> hashMapE = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(intervals[i][0], i);
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < n; i++) {
            int tar = intervals[i][1];
            int l = 0, r = n - 1;
            int t = -1;
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (intervals[mid][0] < tar) l = mid + 1;
                else {
                    t = hashMap.get(intervals[mid][0]);
                    r = mid - 1;
                }
            }
            ans[hashMap.get(intervals[i][0])] = t;
        }
        return ans;
    }
}
