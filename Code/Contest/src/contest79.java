import java.util.*;

public class contest79 {
    public static void main(String[] args) {
//        System.out.println(digitCount("1210"));
        System.out.println(maximumImportance(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}}));
    }

    public static boolean digitCount(String num) {
        int[] map = new int[10];
        for (int i = 0; i < num.length(); i++) {
            map[num.charAt(i) - '0']++;
        }
        for (int i = 0; i < num.length(); i++) {
            if (map[i] != num.charAt(i) - '0') return false;
        }
        return true;
    }

    //Q2
    public static String largestWordCount(String[] messages, String[] senders) {
        class Node {
            String name;
            Integer num;

            public Node(String name, Integer num) {
                this.name = name;
                this.num = num;
            }
        }
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.num != o2.num) {
                    return o2.num - o1.num;
                }
                return o2.name.compareTo(o1.name);
            }
        });
        HashMap<String, Integer> hashMap = new HashMap<>();
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            String[] word = messages[i].split(" ");
            hashMap.put(senders[i], hashMap.getOrDefault(senders[i], 0) + word.length);
        }
        for (Map.Entry<String, Integer> en : hashMap.entrySet()) {
            queue.offer(new Node(en.getKey(), en.getValue()));

        }

        return queue.peek().name;


    }

    //Q3
    public static long maximumImportance(int n, int[][] roads) {

        class Node {
            Integer name;
            Integer num;

            public Node(Integer name, Integer num) {
                this.name = name;
                this.num = num;
            }
        }
        int[] map = new int[n];
        int m = roads.length;
        for (int i = 0; i < m; i++) {
            map[roads[i][0]]++;
            map[roads[i][1]]++;
        }
        Node[] city = new Node[n];
        for (int i = 0; i < n; i++) {
            city[i] = new Node(i, map[i]);
        }
        Arrays.sort(city, (o1, o2) -> o1.num - o2.num);
        HashMap<Integer, Integer> val = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            val.put(city[i].name, i + 1);
        }
        long ans = 0;
        for (int i = 0; i < roads.length; i++) {
            ans += val.get(roads[i][0]) + val.get(roads[i][1]);
        }

        return ans;


    }
}
