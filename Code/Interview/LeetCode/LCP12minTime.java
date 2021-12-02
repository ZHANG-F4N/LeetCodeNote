import java.awt.event.MouseAdapter;
import java.util.Arrays;
import java.util.Map;

public class LCP12minTime {
    public static void main(String[] args) {
        System.out.println(minTime(new int[]{1, 2, 3, 3}, 2));
    }

    // 给定一个数组，将其划分成 M 份，使得每份元素之和最大值最小，
    // 每份可以任意减去其中一个元素。
    // 二分问题的关键要素:   具有单调最优的性质
    public static int minTime(int[] time, int m) {
        /*
         *  二分答案
         *  ans 最大值为 max{time} * len{time} = 1e9
         *  ans 最小值 0
         *
         * */
        if (m >= time.length) {
            return 0;
        }
        int left = 0;
        int right = (int) 1e9;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(time, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static boolean check(int[] time, int m, int T) {
        int maxT = 0, sum = 0, day = 1;
        for (int i = 0; i < time.length; i++) {
            sum += time[i];
            maxT = Math.max(maxT, time[i]);
            if (T < sum - maxT) {
                if (++day > m) return false;
                sum = time[i];
                maxT = time[i];
            }
        }
        return day <= m;
    }
}
