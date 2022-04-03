import javax.print.DocFlavor;
import java.util.*;

public class HuaWei062303 {
//    static class Node {
//        int begin;
//        int end;
//        int weight;
//
//        public Node(int begin, int end, int weight) {
//            this.begin = begin;
//            this.end = end;
//            this.weight = weight;
//        }
//    }
    public static int max;
    public static HashMap<Integer, Integer> res;
    public static void main(String[] args) {
        max = Integer.MAX_VALUE;
        res = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int start[] = {scanner.nextInt(), scanner.nextInt()};
        int end[] = {scanner.nextInt(), scanner.nextInt()};
        int N = scanner.nextInt();
        int[][] map = new int[m][n];
        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            map[x][y] = -1;
        }

        dfs(map, start[0], start[1], end, 0);
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> en : res.entrySet()) {
            if (en.getKey() < min) {
                min = en.getKey();
            }
        }
        System.out.println(res.get(min) + " " + min);
//        Scanner scanner = new Scanner(System.in);
//        int N = Integer.parseInt(scanner.nextLine());
//        HashMap<Integer, ArrayList<Node>> listHashMap = new HashMap<>();
//        HashMap<Integer, Integer> indegreeHashMap = new HashMap<>();
//        for (int i = 1; i <= N; i++) {
//            listHashMap.put(i, new ArrayList<Node>());
//            indegreeHashMap.put(i, 0);
//        }
//
//        int R = Integer.parseInt(scanner.nextLine());
//        for (int i = 0; i < R; i++) {
//            int begin = Integer.parseInt(scanner.next());
//            int end = Integer.parseInt(scanner.next());
//            int weight = Integer.parseInt(scanner.next());
//            listHashMap.get(begin).add(new Node(begin, end, weight));
//        }
//
////        for (int i = 1; i <= N; i++) {
////            for (int j = 0; j < listHashMap.get(i).size(); j++) {
////                System.out.println("begin-> "+listHashMap.get(i).get(j).begin +"  " +"end->"+listHashMap.get(i).get(j).end
////                        +"  "+"weight->"+listHashMap.get(i).get(j).weight);
////            }
////        }
//        //--------BEGIN---拓扑排序------------------
//        int[] TopologicalOrder = new int[N + 1];
//        for (int i = 1; i <= N; i++) {
//            for (int j = 0; j < listHashMap.get(i).size(); j++) {
//                int endPoint = listHashMap.get(i).get(j).end;
//                indegreeHashMap.put(endPoint, indegreeHashMap.get(endPoint) + 1);
//            }
//        }
//        Stack<Integer> stack = new Stack<>();
//        for (Map.Entry<Integer, Integer> entry : indegreeHashMap.entrySet()) {
//            if (entry.getValue() == 0) {
//                stack.push(entry.getKey());
//            }
//        }
//        int count = 1;
//        while (!stack.empty()) {
//            int vexTemp = stack.pop();
//            TopologicalOrder[count++] = vexTemp;
//            ArrayList<Node> tempArray = listHashMap.get(vexTemp);
//            for (int i = 0; i < tempArray.size(); i++) {
//                //System.out.println(tempArray.get(i).begin + " - " + tempArray.get(i).end);
//                indegreeHashMap.replace(tempArray.get(i).end, indegreeHashMap.get(tempArray.get(i).end) - 1);
//                if (indegreeHashMap.get(tempArray.get(i).end) == 0) {
//                    stack.push(tempArray.get(i).end);
//                }
//            }
//        }
//        //-------END---拓扑排序-----------------
//        //System.out.println(count);
//
//        int[] Ve = new int[N + 1];
//        int[] ei = new int[R + 1];
//        int[] el = new int[R + 1];


    }



    public static void dfs(int[][] map, int i, int j, int[] end, int len) {
        if (i >= map.length || i < 0 || j >= map[0].length || j < 0 || len > max || map[i][j] == -1)
            return;
        if (i == end[0] && j == end[1]) {
            if (res.containsKey(len)) res.put(len, res.get(len) + 1);
            else res.put(len, 1);
        }

        map[i][j] = -1;
        dfs(map, i + 1, j, end, len + 1);
        dfs(map, i - 1, j, end, len + 1);
        dfs(map, i, j + 1, end, len + 1);
        dfs(map, i, j - 1, end, len + 1);
        map[i][j] = 0;
    }

}
