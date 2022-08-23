import java.util.ArrayList;
import java.util.List;

public class a655printTree {
    public static void main(String[] args) {
        Integer[] leaf = new Integer[]{1, 2, 3, null, 4};
        TreeNode root = new TreeNode().Build(leaf);
        printTree(root);
    }


    public static int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left) + 1, height(root.right) + 1);
    }

    public static List<List<String>> printTree(TreeNode root) {
        int h = height(root) - 1;
        List<List<String>> ans = new ArrayList<>();
        int n = (1 << (h + 1)) - 1;
        for (int i = 0; i < h + 1; i++) {
            List<String> row = new ArrayList<String>();
            for (int j = 0; j < n; j++) {
                row.add("");
            }
            ans.add(row);
        }
        dfs(root, ans, 0, (n - 1) / 2, h);


        return ans;
    }

    public static void dfs(TreeNode root, List<List<String>> ans, int r, int c, int h) {
        ans.get(r).set(c, Integer.toString(root.val));
        if (root.left != null) dfs(root.left, ans, r + 1, c - (1 << (h - r - 1)), h);
        if (root.right != null) dfs(root.right, ans, r + 1, c + (1 << (h - r - 1)), h);
    }
}
