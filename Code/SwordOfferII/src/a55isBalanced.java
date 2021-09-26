public class a55isBalanced {
    public static void main(String[] args) {
        Integer[] leaf = {3,9,20,null,null,15,7};

        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(isBalanced(root));
    }

    static boolean ans = true;

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        ans = true;
        DFS(root);
        return ans;
    }

    public static int DFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = DFS(root.left);
        int right = DFS(root.right);

        if (ans && Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        } else {
            ans = false;
            return -1;
        }
    }
}
