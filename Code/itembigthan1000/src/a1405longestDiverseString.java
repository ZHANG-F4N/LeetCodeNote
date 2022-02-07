import java.util.PriorityQueue;

public class a1405longestDiverseString {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 7));
        System.out.println(longestDiverseString(7, 1, 0));
    }

    public static String longestDiverseString(int a, int b, int c) {
        StringBuilder ans = new StringBuilder("");
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        if (a > 0) q.add(new int[]{'a', a});
        if (b > 0) q.add(new int[]{'b', b});
        if (c > 0) q.add(new int[]{'c', c});
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int n = ans.length();
            if (n >= 2 && (ans.charAt(n - 1) == ans.charAt(n - 2)) && (ans.charAt(n - 1) == (char) top[0])) {
                if (q.isEmpty()) break;
                int[] cur = q.poll();
                ans.append((char) cur[0]);
                cur[1]--;
                if (cur[1] != 0) q.add(cur);
                q.add(top);
            } else {
                ans.append((char) top[0]);
                top[1]--;
                if (top[1] != 0) q.add(top);
            }
        }

        return ans.toString();
    }
}
