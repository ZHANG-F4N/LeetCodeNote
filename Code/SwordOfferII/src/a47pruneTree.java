import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IllegalFormatCodePointException;

public class a47pruneTree {
    public static void main(String[] args) {
        Integer[] leaf = {1, 0, 1, 0, 0, 0, 1};
        TreeNode root = new TreeNode().Build(leaf);
        pruneTree(root);
    }

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}
