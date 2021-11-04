import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class a74merge {
    public static void main(String[] args) {
        merge(new int[][]{{4, 5}, {2, 4}, {4, 6}, {3, 4}, {0, 0}, {1, 1}, {3, 5},{2,2}});
        merge(new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}});
        merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        merge(new int[][]{{0, 0}, {1, 2}, {5, 5}, {2, 4}, {3, 3}, {5, 6}, {5, 6}, {4, 6}, {0, 0}, {1,
                2}, {0, 2}, {4, 5}});
        merge(new int[][]{{0, 0}, {0, 0}, {0, 5}});
        merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}});

        merge(new int[][]{{1, 4}, {4, 5}});

    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals,(o1, o2) -> o1[0]-o2[0]);
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
