import java.util.*;

public class a933RecentCounter {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        int ans = recentCounter.ping(1);
        ans = recentCounter.ping(100);
        ans = recentCounter.ping(3001);
        ans = recentCounter.ping(3002);
    }

    static class RecentCounter {

        Queue<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<Integer>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }
}
