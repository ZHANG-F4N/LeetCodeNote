import java.util.ArrayList;

public class a235lowestCommonAncestor {
    public static void main(String[] args) {
        Integer[] leaf = new Integer[]{6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(lowestCommonAncestor(root, root.left, root.left.right).val);
    }

    /*
     *
     *
     * */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }

    }
}
