import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LeetCode0925a1 {

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        set.add(root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    set.add(node.left.val);
                    queue.add(node.left);
                }
                if (node.right != null) {
                    set.add(node.right.val);
                    queue.add(node.right);
                }
            }
        }
        return set.size();
    }
}
