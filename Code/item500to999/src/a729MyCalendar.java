import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class a729MyCalendar {
    static class MyCalendar {
        //        PriorityQueue<int[]> calendar;
        ArrayList<int[]> calendar;

        public MyCalendar() {
            calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            if (calendar.size() == 0) {
                calendar.add(new int[]{start, end});
                return true;
            }
            int l = 0, r = calendar.size() - 1;
            while (l < r) {
                int m = l + r >>> 1;
                int[] event = calendar.get(m);
                if (event[0] > start) {
                    l = m + 1;
                } else if (event[1] > start) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            if (r == calendar.size() - 1) {


                if (calendar.get(r - 1)[1] <= start && calendar.get(r)[0] < end) {
                    calendar.add(r, new int[]{start, end});
                    return true;
                }
                if (calendar.get(r)[1] <= start) {
                    calendar.add(r + 1, new int[]{start, end});
                    return true;
                }

            } else {
                if (calendar.get(r + 1)[0] < end) {
                    calendar.add(r, new int[]{start, end});
                    return true;
                }
            }

            return false;

        }


    }

    //            [[],[47,50],[33,41],[39,45],[33,42],[25,32],[26,35],[19,25],[3,8],[8,13],[18,27]]
    public static void main(String[] args) {
        MyCalendar obj = new MyCalendar();
        System.out.println(obj.book(47, 50));
        System.out.println(obj.book(33, 41));
        System.out.println(obj.book(39, 45));

    }
}
