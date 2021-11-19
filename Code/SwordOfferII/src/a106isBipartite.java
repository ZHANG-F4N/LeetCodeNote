import java.util.HashSet;
import java.util.Iterator;

public class a106isBipartite {
    public static void main(String[] args) {
//        int[][] graph = {{4}, {}, {4}, {4}, {0, 2, 3}};
//        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        int[][] graph = {{1}, {0, 3}, {3}, {1, 2}};
//        int[][] graph = {{1,  3}, {0, 2}, { 1, 3}, {0, 2}};
        System.out.println(isBipartite(graph));
    }

    public static boolean ans;

    public static boolean isBipartite(int[][] graph) {
        ans = true;
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length && ans; i++) {
            if (color[i] == 0) DFS(graph, color, i, 1);
        }
        return ans;
    }

    // 第i个节点为初始节点.
    public static void DFS(int[][] graph, int[] color, int i, int rORg) {
        if (color[i] != 0) {// 节点已经染过色
            if (color[i] != rORg) ans = false;
            return;
        }
        color[i] = rORg;
        for (int i1 : graph[i]) {
            // 给与 i 相邻的节点都染上相反的颜色
            DFS(graph, color, i1, -rORg);
        }
    }
}
