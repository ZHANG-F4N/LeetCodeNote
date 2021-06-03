import java.util.*;

public class a103zigzagLevelOrder {
    public static void main(String[] args) {
        Integer[] nums = {3, 9, 20, null, null, 15, 7};
        TreeNode root = new TreeNode();
        root = root.Build(nums);
        zigzagLevelOrder(root);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ansList = new ArrayList<>();
        Deque<TreeNode> nodeDeque = new ArrayDeque<>();
        if (root == null) {
            return ansList;
        }
        nodeDeque.push(root);
        int level = 0;
        while (!nodeDeque.isEmpty()) {
            int size = nodeDeque.size();
            Deque<Integer> list = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = nodeDeque.poll();
                if (level % 2 == 0) {
                    list.offerLast(temp.val);
                } else {
                    list.offerFirst(temp.val);
                }
                if (temp.left != null) {
                    nodeDeque.offer(temp.left);
                }
                if (temp.right != null) {
                    nodeDeque.offer(temp.right);
                }
            }
            level++;
            ansList.add(new ArrayList<>(list));
        }
        return ansList;
    }
}
