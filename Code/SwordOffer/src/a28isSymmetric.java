public class a28isSymmetric {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 2, 3, 4, 4, 3};
        TreeNode tree = new TreeNode().Build(nums);
        System.out.println(isSymmetric(tree));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return judge(root.left, root.right);
    }

    public static boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return judge(left.left, right.right) && judge(left.right, right.left);
        } else {
            return false;
        }
    }
}
