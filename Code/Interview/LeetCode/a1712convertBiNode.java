public class a1712convertBiNode {
    public static void main(String[] args) {
        Integer[] leaf = {4, 2, 5, 1, 3, null, 6, 0};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(convertBiNode(root));
    }

    public static TreeNode head;

    public static TreeNode convertBiNode(TreeNode root) {
        head = null;
        inOrder(root, null);
        return head;
    }

    public static void inOrder(TreeNode root, TreeNode next) {
        if (root == null) return;
        inOrder(root.left, null);
        if (next == null) head = root;
        else root.right = next;
        inOrder(root.right, root);
    }
}
