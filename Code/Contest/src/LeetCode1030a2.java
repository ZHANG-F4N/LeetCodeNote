import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LeetCode1030a2 {
    public static void main(String[] args) {
//        System.out.println(maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {2, 4, 3}}));
//        System.out.println(maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {1, 5, 5}}));
        System.out.println(maxTwoEvents(new int[][]{{10, 83, 53}, {63, 87, 45}, {97, 100, 32}, {51, 61, 16}}));
    }

    public static int maxTwoEvents(int[][] events) {
        int N = events.length;

        int max = 0;
        // 先求一个活动的最大值
        Arrays.sort(events, (o1, o2) -> o2[2] - o1[2]);
        max = events[0][2];
        for (int i = 0; i < N; i++) {
            boolean flag = false;
            for (int j = i + 1; j < N; j++) {
                // i 前 j 后
                int val = events[i][2] + events[j][2];
                if (val < max){
                    break;
                }
                if (events[i][1] < events[j][0]) {
//                    flag = true;
                    max = max > val ? max : val;
                    break;
                }
                // i 后 j 前
                if (events[i][0] > events[j][1]) {
//                    flag = true;
                    max = max > val ? max : val;
                    break;
                }
            }
            return max;
        }
        return  max;

    }
}
