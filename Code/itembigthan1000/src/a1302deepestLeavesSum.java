import java.util.ArrayDeque;
import java.util.ArrayList;

public class a1302deepestLeavesSum {
    public static void main(String[] args) {
        Integer[] leaf = new Integer[]{1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(deepestLeavesSum(root));
    }

    public static int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;

        ArrayList<TreeNode> deque = new ArrayList<>();
        deque.add(root);
        int last = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<TreeNode> next = new ArrayList<>();
            int val = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.get(i);
                val += node.val;
                if (node.left != null) next.add(node.left);
                if (node.right != null) next.add(node.right);
            }
            deque = next;
            last = val;
        }
        return last;
    }
}
