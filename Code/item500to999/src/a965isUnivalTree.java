public class a965isUnivalTree {
    public static void main(String[] args) {

    }

    public static int val;
    public static boolean ans;

    public boolean isUnivalTree(TreeNode root) {
        val = root.val;
        ans = true;
        preOrder(root);
        return ans;
    }

    public static void preOrder(TreeNode root) {
        if (root == null || !ans) return;
        if (root.val != val) ans = false;
        preOrder(root.left);
        preOrder(root.right);
    }
}
