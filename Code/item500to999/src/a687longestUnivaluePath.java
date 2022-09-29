public class a687longestUnivaluePath {
    public static void main(String[] args) {
        Integer[] leaf = new Integer[]{1, 4, 5, 4, 4, 5};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(longestUnivaluePath(root));
    }

    public static int ans = 0;

    public static int longestUnivaluePath(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public static int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left), r = dfs(root.right);
        int l1 = 0, r1 = 0;
        if (root.left != null && root.val == root.left.val) {
            l1 = l + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            r1 = r + 1;
        }

        ans = Math.max(ans, l1 + r1);
        return Math.max(l1, r1);
    }
}
