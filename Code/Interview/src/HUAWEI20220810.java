import java.util.*;

public class HUAWEI20220810 {

    public static List<Integer>[] map;
    public static char[] colorArr;

    public static int[] visited;

//    public static int dfs(int idx, List<Integer> childs, int L, int R) {
//
//        if (visited[idx] == 1)
//            return 0;
//
//        visited[idx] = 1;
//        if (childs == null)
//            return Math.abs(L - R);
//        else {
//            L += colorArr[idx] == 'R' ? 1 : 0;
//            R += colorArr[idx] == 'B' ? 1 : 0;
//            int temp = Math.abs(L - R);
//            for (int i : childs) {
//                temp += dfs(i, map[i], L, R);
//            }
//            return temp;
//        }
//
//    }

    public static int dfs(List<Integer> list, int idx, int L, int R) {
        if (visited[idx] == 1) return 0;
        visited[idx] = 1;
        if (list == null) {
            return Math.abs(L - R);
        } else {
            L += colorArr[idx] == 'B' ? 1 : 0;
            R += colorArr[idx] == 'R' ? 1 : 0;
            int temp = Math.abs(L-R);
            for (int i = 0; i < list.size(); i++) {
                temp += dfs(map[list.get(i)], list.get(i), L, R);
            }
            return temp;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        color = "0" + color;
        map = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            map[v1].add(v2);
            map[v2].add(v1);
        }
        visited = new int[n + 1];
        colorArr = color.toCharArray();

//        int[] L = new int[n + 1];
//        int[] R = new int[n + 1];
//
//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        deque.addLast(1);
//        while (!deque.isEmpty()) {
//            int size = deque.size();
//            for (int i = 0; i < size; i++) {
//                int val = deque.getFirst();
//                List<Integer> list = map[val];
//                for (int j = 0; j < list.size(); j++) {
//                    int ch = list.get(0);
//                    if (visited[ch] == 1) continue;
//                    if (colorArr[ch] == 'L') L[]
//                }
//            }
//        }


        System.out.println(dfs(map[1], 1, 0, 0));


    }

}
