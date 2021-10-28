import javax.swing.table.TableRowSorter;
import java.util.HashMap;
import java.util.Map;

public class a50pathSum {
    public static void main(String[] args) {
        Integer[] leaf = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(pathSum(root, 8));
    }

    //每一条路径是一维的,<前缀和,前缀和为此值的个数>
    static Map<Integer, Integer> hashMap;
    static int ans, tar;

    // HashMap保存前缀和
    public static int pathSum(TreeNode root, int targetSum) {

        hashMap = new HashMap<>();
        ans = 0;
        if (root == null) {
            return 0;
        }
        tar = targetSum;
        hashMap.put(0, 1);
        DFS(root, root.val);
        return ans;
    }

    // sum 从源节点到当前节点的和
    static void DFS(TreeNode root, int sum) {
        if (hashMap.containsKey(sum - tar)) {
            ans += hashMap.get(sum - tar);
        }
        hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);

        if (root.left != null) DFS(root.left, sum + root.left.val);
        if (root.right != null) DFS(root.right, sum + root.right.val);

        hashMap.put(sum, hashMap.getOrDefault(sum, 0) - 1);
    }

}
