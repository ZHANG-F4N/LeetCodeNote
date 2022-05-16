public class a0406inorderSuccessor {
    public static void main(String[] args) {
//        Integer[] leaf = {5, 3, 6, 2, 4, null, null, 1};
//        Integer[] leaf = {2, null, 3};
        Integer[] leaf = {2, 1, 3};
//        Integer[] leaf = {5, 3, 6, 2, 4, null, null, 1};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(inorderSuccessor2(root, root.left));
    }


    public static TreeNode ans;
    public static boolean flag;

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        ans = null;
        flag = false;
        inOrder(root, p);
        return ans;
    }

    public static void inOrder(TreeNode root, TreeNode p) {
        if (root == null) return;
        inOrder(root.left, p);
        if (flag && ans == null) {
            ans = root;
            return;
        }
        if (root == p) {
            flag = true;
        }
        inOrder(root.right, p);
    }

    public static TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        if (p.right != null) {
            ans = p.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return ans;
        }
        TreeNode t = root;
        while (t != null) {
            if (t.val > p.val) {
                ans = t;
                t = t.left;

            } else  {
                t = t.right;
            }
        }
        return ans;
    }


}
