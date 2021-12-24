import java.util.PriorityQueue;

public class a1705eatenApples {
    public static void main(String[] args) {
        System.out.println(eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}));
        System.out.println(eatenApples(new int[]{2, 1, 10}, new int[]{2, 10, 1}));
        System.out.println(eatenApples(new int[]{3, 0, 0, 0, 0, 2}, new int[]{3, 0, 0, 0, 0, 2}));

    }

    public static int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int n = apples.length;
        int time = 0;
        int ans = 0;
        while (time < n || !queue.isEmpty()) {
            if (time < n && days[time] != 0 && apples[time] != 0)
                queue.offer(new int[]{apples[time], days[time] + time - 1});

            while (!queue.isEmpty() && queue.peek()[1] < time) queue.poll();

            if (!queue.isEmpty()){
                int[] now = queue.poll();
                if (--now[0] > 0 && now[1] > time) queue.offer(now);
                ans++;
            }
            time++;
        }
        return ans;
    }
}
