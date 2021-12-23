public class a1712convertBiNode {
    public static void main(String[] args) {
//        Integer[] leaf = {4, 2, 5, 1, 3, null, 6, 0};
        Integer[] leaf = {8, 4, 10, 2, 6, 9, 11, 1, 3, 5, 7};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(convertBiNode(root));
    }

    public static TreeNode head;
    public static TreeNode pre;


    public static TreeNode convertBiNode(TreeNode root) {
        head = null;
        pre = null;
        inOrder(root);
        return head;
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        root.left = null;
        if (pre == null) {
            head = root;
        } else {
            pre.right = root;
        }
        pre = root;
        inOrder(root.right);
    }
}
