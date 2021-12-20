import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class a1610visiblePoints {
    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        points.add(Arrays.asList(new Integer[]{2, 1}));
        points.add(Arrays.asList(new Integer[]{2, 2}));
        points.add(Arrays.asList(new Integer[]{3, 3}));
        System.out.println(visiblePoints(points, 90, Arrays.asList(new Integer[]{1, 1})));
    }

    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int n = points.size();
        int pox = location.get(0);
        int poy = location.get(1);

        int sameCnt = 0;
        // cos(x) ~ [-1,1]
        //double[] ang = new double[n];
        ArrayList<Double> ang = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = points.get(i).get(0);
            int y = points.get(i).get(1);
            if (x == pox && y == poy) {
                sameCnt++;
                continue;
            }
            ang.add(Math.atan2(y - poy, x - pox));
        }

        Collections.sort(ang);

        int m = ang.size();
        // 因为360是循环的,自转可以顺时针和逆时针两个方向转
        // 所以 将角度拓展为两倍, 遍历一次即可
        // [1   2   3   4]   循环搜索就可以用常规线性搜索
        // [1   2   3   4   1   2   3   4]
        for (int i = 0; i < m; i++) {
            ang.add(ang.get(i) + Math.PI * 2);
        }

        int ans = 0;
        int right = 0;
        // 将角度 π 形式转换为 度
        // π = 180
        double toDegree = angle * Math.PI / 180;
        for (int i = 0; i < m; ++i) {
            Double curr = ang.get(i) + toDegree;
            while (right < ang.size() && ang.get(right) <= curr) {
                right++;
            }
            ans = Math.max(ans, right - i);
        }
        return ans + sameCnt;
    }
}
