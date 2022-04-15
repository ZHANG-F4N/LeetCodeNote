import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HuaWei20220406 {
    public static List<Integer>[] map;
    public static int[] visited;
    public static List<Integer> ans;
    public static boolean valid = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        map = new ArrayList[N];
        visited = new int[N];
        ans = new ArrayList<Integer>(N);
        int start = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            String[] split = scanner.next().split(",");
            int n = Integer.parseInt(split[0]);
            ArrayList<Integer> list = new ArrayList<>();
            map[i] = list;
            if (n == 0) continue;
            for (int j = 1; j <= n; j++) {
                list.add(Integer.parseInt(split[j]));
            }

        }
        dfs(start);

        if (!valid) {
            System.out.println("-1");
            return;
        }
        if (ans.size() == 1) System.out.println("null");
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            if (ans.get(i) == start) continue;
            System.out.print(ans.get(i) + " ");
        }
    }


    public static void dfs(int start) {
        visited[start] = 1;

        for (Integer v : map[start]) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) return;
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[start] = 2;
        ans.add(start);
    }

}
