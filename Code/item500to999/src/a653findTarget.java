import java.nio.charset.IllegalCharsetNameException;
import java.util.HashSet;

public class a653findTarget {
    public static void main(String[] args) {
        Integer[] leaf = {5, 3, 6, 2, 4, null, 7};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(findTarget(root, 29));
    }

    private static HashSet<Integer> set = new HashSet<>();

    public static boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    public static boolean pre(TreeNode root, int k) {
        if (root == null) return false;
        if (root.val == k) return true;
        if (k > root.val) return pre(root.right, k);
        if (k < root.val) return pre(root.left, k);
        return false;
    }
}
