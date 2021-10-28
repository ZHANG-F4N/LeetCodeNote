public class a54convertBST {
    public static void main(String[] args) {
        Integer[] leaf = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(convertBST(root).val);
    }

    static int ans;

    public static TreeNode convertBST(TreeNode root) {

        ans = 0;
        reverseOrder(root);
        return root;
    }

    public static void reverseOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        reverseOrder(root.right);
        ans += root.val;
        root.val = ans;

        reverseOrder(root.left);
    }
}
