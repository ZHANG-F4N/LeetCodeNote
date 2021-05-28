import java.util.ArrayDeque;

public class a98isValidBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Integer[] nums = {5, 1, 4, null, null, 3, 6};
        root = root.Build(nums);
        System.out.println(isValidBST(root));
    }

    private static long pre = Long.MIN_VALUE;

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            if (pre >= root.val) {
                return false;
            }
            pre = root.val;
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }


}
