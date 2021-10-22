import java.util.*;

public class LeetCode1020a3 {


    public static void main(String[] args) {
//        int[][] edges = {{0, 1}, {0, 2}, {1, 2}};
//        int[] patience = {0, 10, 10};

        int[] patience = {0, 2, 1};
        int[][] edges = {{0, 1}, {1, 2}};

        System.out.println(networkBecomesIdle(edges, patience));
    }

    private static HashMap<Integer, ArrayList<Integer>> map;

    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        int N = edges.length;
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (map.containsKey(edges[i][0])) {
                ArrayList<Integer> temp = map.get(edges[i][0]);
                temp.add(edges[i][1]);
            } else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(edges[i][1]);
                map.put(edges[i][0], temp);
            }
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        BFS(hashMap, map);
        int ans = 0;
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            int distance = en.getValue();
            // 算时间
            int Time = ((distance * 2) - 1) / patience[en.getKey()] * patience[en.getKey()];
            ans = Math.max(ans, 2 * distance + Time + 1);
        }
        return ans;
    }

    public static void BFS(HashMap<Integer, Integer> hashMap, HashMap<Integer, ArrayList<Integer>> map) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int deep = 1;
        deque.offer(0);
        while (!deque.isEmpty()) {
            int node = deque.poll();
            if (map.containsKey(node)) {
                ArrayList<Integer> temp = map.get(node);
                Iterator<Integer> it = temp.iterator();
                while (it.hasNext()) {
                    int next = it.next();
                    if (hashMap.containsKey(next)) {
                        continue;
                    }else {
                        deque.offer(next);
                        hashMap.put(next, deep);
                    }
                }

            }
            deep++;
        }
    }
}
