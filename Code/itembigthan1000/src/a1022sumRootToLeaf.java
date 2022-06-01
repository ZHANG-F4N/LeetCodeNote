import javax.print.DocFlavor;

public class a1022sumRootToLeaf {

    public static void main(String[] args) {
        Integer[] leaf = {1, 0, 1, 0, 1, 0, 1};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(sumRootToLeaf(root));
    }

    public static int ans;

    public static int sumRootToLeaf(TreeNode root) {
        ans = 0;
        dfs(root, 0, 0);
        return ans;
    }

    public static void dfs(TreeNode root, int val, int deep) {
        if (root == null) return;
        val = ((val << 1) | root.val);
        if (root.left == null && root.right == null) {
            ans += val;
            return;
        }
        dfs(root.left, val, deep + 1);
        dfs(root.right, val, deep + 1);
    }
}
