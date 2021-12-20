import java.util.Arrays;
import java.util.PriorityQueue;

public class a630scheduleCourse {
    public static void main(String[] args) {
        System.out.println(scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250},
                {2000, 3200}}));
    }

    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<int[]> learned = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int beg = 0;
        for (int[] cours : courses) {
            
            learned.offer(cours);
            if (beg + cours[0] <= cours[1]) {
                // 如果满足时间约束
                beg += cours[0];
            } else {
                // 不满足时间约束
                beg -= learned.poll()[0];
            }

        }
        return learned.size();
    }
}
