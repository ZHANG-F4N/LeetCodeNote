import java.util.HashMap;
import java.util.Map;

public class a447numberOfBoomerangs {
    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        System.out.println(numberOfBoomerangs2(points));
    }


    public static int numberOfBoomerangs2(int[][] points) {
        int res = 0;
        for (int[] point : points) {

            HashMap<Integer, Integer> hashMap = new HashMap<>();

            for (int[] ints : points) {
                int dis = (point[0] - ints[0]) * (point[0] - ints[0]) + (point[1] - ints[1]) * (point[1] - ints[1]);
                hashMap.put(dis, hashMap.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {

                res += en.getValue() * (en.getValue() - 1);
            }
        }
        return res;
    }


    //回溯 超时

    static int ans = 0;

    public static int numberOfBoomerangs(int[][] points) {

        ans = 0;
        int N = points.length;
        if (N < 3) {
            return ans;
        }
        trackBack(points, new int[3][2], 0);
        return ans;

    }

    public static void trackBack(int[][] points, int[][] select, int num) {
        if (num > 3) {
            return;
        }
        if (num == 3) {
            double dis1 = (select[0][0] - select[1][0]) * (select[0][0] - select[1][0]) + (select[0][1] - select[1][1]) * (select[0][1] - select[1][1]);
            double dis2 = (select[1][0] - select[2][0]) * (select[1][0] - select[2][0]) + (select[1][1] - select[2][1]) * (select[1][1] - select[2][1]);
            if (Double.compare(dis1, dis2) == 0) {
                ans++;
            }
            return;
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == Integer.MAX_VALUE) {
                continue;
            }
            int[] selTemp = select[num];
            select[num][0] = points[i][0];
            select[num][1] = points[i][1];
            int temp = points[i][0];
            points[i][0] = Integer.MAX_VALUE;

            trackBack(points, select, num + 1);

            points[i][0] = temp;
            select[num] = selTemp;
        }

    }
}
