public class a053inorderSuccessor {
    public static void main(String[] args) {
//        Integer[] leaf = {5, 3, 6, 2, 4, null, null, 1};
        Integer[] leaf = {2, 1, 3};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(inorderSuccessor(root, root.left).val);
    }

    static TreeNode ans;
    static boolean isP;

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        isP = false;
        ans = null;
        //TreeNode pre = null;
        inOrder(root, p);
        return ans;
    }

    public static void inOrder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        inOrder(root.left, p);
        if (isP) {
            ans = root;
            isP = false;
        }
        if (p == root) {
            isP = true;
        }
        inOrder(root.right, p);
    }
}
