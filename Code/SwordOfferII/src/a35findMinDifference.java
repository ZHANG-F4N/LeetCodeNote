import javax.swing.text.ViewFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a35findMinDifference {
    public static void main(String[] args) {
//        String[] timePoints = {"00:00", "23:59", "00:00"};
//        String[] timePoints = {"00:00", "23:59"};
//        String[] timePoints = {"01:01", "02:01"};
        String[] timePoints = {"01:01", "02:01", "03:00"};
//        List<String> list = new ArrayList<String>();
        List<String> list = Arrays.asList(timePoints);
        System.out.println(findMinDifference(list));

    }

    public static int findMinDifference(List<String> timePoints) {
        int[] time = new int[1441];

        Iterator<String> it = timePoints.iterator();
        while (it.hasNext()) {
            String[] str = it.next().split(":");
            int temp = (Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]));
            time[temp]++;
            if (time[temp] >= 2) {
                return 0;
            }
        }
        int min = 2000;
        int l = -1;
        int r = -1;
        int ll = -1;
        for (int i = 0; i < time.length; i++) {
            if (time[i] == 0) {
                continue;
            }
            if (l == -1) {
                l = i;
                ll = i;
                continue;
            }
            r = i;
            int ans = r - l;
            if (ans > 720) {
                ans = 1440 - ans;
            }
            l = i;
            min = min > ans ? ans : min;
        }
        int ans = r - ll;
        if (ans > 720) {
            ans = 1440 - ans;
        }
        min = min > ans ? ans : min;
        return min;


    }
}
