import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class contest65a3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6})));
    }

    public static int[] maximumBeauty(int[][] items, int[] queries) {
        HashMap<Integer, Integer> treeMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            if (treeMap.containsKey(items[i][0])) {
                int max = treeMap.get(items[i][0]) > items[i][1] ? treeMap.get(items[i][0]) : items[i][1];
                treeMap.put(items[i][0], max);
            } else {
                treeMap.put(items[i][0], items[i][1]);
            }
        }
        int num = treeMap.size();
        int[] max = new int[num];
        int[][] maxVal = new int[num][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            max[idx] = entry.getKey();
            maxVal[idx][1] = entry.getValue();
            maxVal[idx][0] = entry.getKey();
            idx++;
        }
        Arrays.sort(maxVal, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(max);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            idx = Arrays.binarySearch(max, queries[i]);
            if (idx < 0) {
                idx = -idx -2;
            }
            ans[i] = maxVal[idx][1];
        }
        return ans;
    }
}