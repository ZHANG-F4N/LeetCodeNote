public class a226invertTree {
    public static void main(String[] args) {

    }
    public static TreeNode invertTree(TreeNode root) {
        reverse(root);
        return root;
    }
    public static void reverse(TreeNode root) {
        if (root == null) return;
        reverse(root.left);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        reverse(root.right);
    }
}
