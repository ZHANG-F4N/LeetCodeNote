import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class a0409BSTSequences {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        int num = 1;
        deque.add(root);
        while (!deque.isEmpty()) {
            num--;
            TreeNode temp = deque.peekFirst();
            deque.offerLast(temp.left);
            deque.offerLast(temp.right);
            if (num == 0) {
                num = deque.size();
                List<Integer> level = new ArrayList<>();
                level.add(root.val);
            }
        }
        return ans;
    }
}
