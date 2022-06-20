import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCodeCup2022Spring {
    public static void main(String[] args) {
//        System.out.println(giveGem(new int[]{100, 0, 50, 100}, new int[][]{{0, 2}, {0, 1}, {3, 0}, {3, 0}}));
//        System.out.println(giveGem(new int[]{0, 0, 0, 0}, new int[][]{{1, 2}, {3, 1}, {1, 2},
//                {3, 0}}));
//        System.out.println(perfectMenu(new int[]{10, 10, 10, 10, 10}, new int[][]{{1, 1, 1, 1, 1}, {3, 3, 3, 3
//                , 3}, {10, 10, 10, 10, 10}}, new int[][]{{5, 5}, {6, 6}, {10, 10}}, 1));
        Integer[] leaf = {4, 2, 7, 1, null, 5, null, null, null, null, 6};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(getNumber(root, new int[][]{{0, 2, 2}, {1, 1, 5}, {0, 4, 5}, {1, 5, 7}}));
    }

    // Q1
    public static int giveGem(int[] gem, int[][] operations) {
        int n = operations.length;
        for (int i = 0; i < n; i++) {
            int from = operations[i][0];
            int to = operations[i][1];
            int half = gem[from] / 2;
            gem[from] -= half;
            gem[to] += half;
        }
        int max = gem[0];
        int min = gem[0];
        for (int i = 0; i < gem.length; i++) {
            max = Math.max(max, gem[i]);
            min = Math.min(min, gem[i]);
        }
        return max - min;

    }

    public static int ans;

    //Q2
    public static int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        ans = -1;
        dfs(materials, cookbooks, attribute, limit, 0, 0, 0);
        return ans;
    }

    public static void dfs(int[] materials, int[][] cookbooks, int[][] attribute, int limit, int i
            , int maxVal, int enough) {
        if (i >= cookbooks.length) {
            if (enough >= limit) ans = Math.max(ans, maxVal);
            return;
        }
        boolean ok = true;
        for (int k = 0; k < 5; k++) {
            if (cookbooks[i][k] > materials[k]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            for (int k = 0; k < 5; k++) {
                materials[k] -= cookbooks[i][k];
            }
            maxVal += attribute[i][0];
            enough += attribute[i][1];
            dfs(materials, cookbooks, attribute, limit, i + 1, maxVal, enough);
            for (int k = 0; k < 5; k++) {
                materials[k] += cookbooks[i][k];
            }
            maxVal -= attribute[i][0];
            enough -= attribute[i][1];
        }
        dfs(materials, cookbooks, attribute, limit, i + 1, maxVal, enough);
    }

    // Q3
    public static int getNumber(TreeNode root, int[][] ops) {
        ArrayList<Integer> node = new ArrayList<>();
        preOrder(root, node);
        int[] list = new int[node.size()];
        for (int i = 0; i < node.size(); i++) {
            list[i] = node.get(i);
        }

//        // 差分数组
        int n = list.length;
//        int[] dx = new int[n + 1];
//        for (int i = 1; i < n; i++) {
//            dx[i] = list[i] - list[i - 1];
//        }

        boolean[] flag = new boolean[n];
        int M = ops.length;
        for (int i = 0; i < M; i++) {
            int left = Arrays.binarySearch(list, ops[i][1]);
            int right = Arrays.binarySearch(list, ops[i][2]);
            for (int j = left; j <= right; j++) {
                if (ops[i][0] == 0) {
                    if (!flag[j]) continue;
                    else flag[j] = !flag[j];
                } else {
                    if (flag[j]) continue;
                    else flag[j] = !flag[j];
                }
            }
        }

//        for (int i = 0; i < M; i++) {
//            int val = ops[i][0] == 0 ? -1 : 1;
//            int left = Arrays.binarySearch(list, ops[i][1]);
//            int right = Arrays.binarySearch(list, ops[i][2]);
//            dx[left] += val;
//            dx[right + 1] -= val;
//        }

//        int[] ansArr = new int[n];
//        ansArr[0] = list[0] + dx[0];
//        for (int i = 1; i < n; i++) {
//            ansArr[i] = ansArr[i - 1] + dx[i];
//        }
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            if (ansArr[i] > list[i]) ans++;
//        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (flag[i]) ans++;
        }
        return ans;
    }


    public static void preOrder(TreeNode root, List<Integer> node) {
        if (root == null) return;
        preOrder(root.left, node);
        node.add(root.val);
        preOrder(root.right, node);
    }

}
