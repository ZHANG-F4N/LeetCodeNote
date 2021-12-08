import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class a0401findWhetherExistsPath {
    public static void main(String[] args) {
        System.out.println(findWhetherExistsPathBrief(5, new int[][]{{0, 1}, {0, 2}, {0, 4}, {0, 4}, {0, 1},
                {1, 3}, {1, 4}, {1, 3}, {2, 3}, {3, 4}}, 0, 4));
    }

    public static boolean findWhetherExistsPathBrief(int n, int[][] graph, int start, int target) {
        if (start == target) return true;
        for (int[] en : graph) {
            if (en[1] == target) {
                return findWhetherExistsPathBrief(n, graph, start, en[0]);
            }
        }
        return false;
    }

    public static boolean ans;

    public static boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            if (x == y) continue;
            ArrayList<Integer> list;
            if (map.containsKey(x)) {
                list = map.get(x);
                if (list.lastIndexOf(y) == -1)
                    list.add(y);
            } else {
                list = new ArrayList<>();
                list.add(y);
            }
            map.put(x, list);
        }

        ans = false;
        DFS(map, start, target);
        return ans;
    }

    public static void DFS(HashMap<Integer, ArrayList<Integer>> map, int start, int target) {
        if (ans) return;
        if (!map.containsKey(start)) {
            return;
        }
        ArrayList<Integer> list = map.get(start);
        for (int i = 0; i < list.size(); i++) {
            int tar = list.get(i);
            if (tar == target) {
                ans = true;
                return;
            }
            DFS(map, tar, target);
        }
    }
}
