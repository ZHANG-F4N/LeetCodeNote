import java.util.HashMap;

public class a2049countHighestScoreNodes {
    public static void main(String[] args) {
        System.out.println(countHighestScoreNodes(new int[]{-1, 2, 0}));
        System.out.println(countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0}));
    }

    // 记录每个节点的左右子节点
    private static int[] L, R;
    private static long maxProduct;
    private static int maxCount, n;

    public static int countHighestScoreNodes(int[] parents) {
        // 以i为父节点的子节点个数
        n = parents.length;
        L = new int[n];
        R = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            L[i] = R[i] = -1;
        }
        for (int node = 1; node < n; node++) {
            // 其实谁是左节点谁是右节点根本无所谓
            if (L[parents[node]] == -1) {
                L[parents[node]] = node;
            } else {
                R[parents[node]] = node;
            }
        }

//        int[] node = new int[n];
//        HashMap<Integer, int[]> hashMap = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            int pre = i;
//            while (parents[pre] != -1) {
//                node[parents[pre]]++;
//                pre = parents[pre];
//            }
//            if (parents[i] != -1) {
//                int[] temp;
//                if (hashMap.containsKey(parents[i])) {
//                    temp = hashMap.get(parents[i]);
//                    temp[1] = i;
//                } else {
//                    temp = new int[]{i, -1};
//                }
//                hashMap.put(parents[i], temp);
//            }
//        }

//        int ans = 0;
//        int max = 0;
//
//        for (int i = 0; i < n; i++) {
//            int f = L[0] - [i];
//            int l = 0;
//            int r = 0;
//            int[] temp = hashMap.get(i);
//            if (temp != null) {
//                l = node[temp[0]] + 1;
//                r = temp[1] == -1 ? 0 : node[temp[1]] + 1;
//            }
//            if (f == 0) f = 1;
//            if (r == 0) r = 1;
//            if (l == 0) l = 1;
//            int score = l * f * r;
//            parents[i] = score;
//            if (score > max) max = score;
//        }
//        for (int i = 0; i < n; i++) {
//            if (parents[i] == max) ans++;
//        }
        dfs(0);
        return maxCount;
    }


    // dfs 求子树节点个数
    static int dfs(int node) {
        // 空节点
        if (node == -1) {
            return 0;
        }
        // 左右子树的节点数量
        int l = dfs(L[node]);
        int r = dfs(R[node]);
        // 左子树 * 右子树 * 父节点所在的数，但是如果没有树的情况返回的结果就是0，那就转换为1，不影响
        long product = (long) Math.max(l, 1) * Math.max(r, 1) * Math.max(n - l - r - 1, 1);

        // 更新结果
        if (maxProduct < product) {
            maxProduct= product;
            maxCount = 1;
        } else if (maxProduct == product) {
            maxCount++;
        }
        return l + r + 1;
    }
}
