public class a998insertIntoMaxTree {
    public static void main(String[] args) {

    }

    public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root != null) return new TreeNode(val);
        if (root.val < val) {
            TreeNode cur = new TreeNode(val);
            cur.left = root;
            return cur;
        } else {
            root.right = insertIntoMaxTree(root.right, val);
        }
        return root;
    }

}
