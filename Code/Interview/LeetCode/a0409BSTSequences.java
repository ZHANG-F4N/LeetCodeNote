import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class a0409BSTSequences {
    public static void main(String[] args) {
        Integer[] leaf = {2, 1, 3};
        TreeNode root = new TreeNode().Build(leaf);
        BSTSequences(root);
    }

    public static List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<TreeNode> deque = new ArrayDeque<>();
        int num = 1;
        deque.add(root);
        List<Integer> level = new ArrayList<>();
        while (!deque.isEmpty()) {
            num--;
            TreeNode temp = deque.pollFirst();
            level.add(temp.val);
            if (temp.left != null) deque.offerLast(temp.left);
            if (temp.right != null) deque.offerLast(temp.right);
            if (num == 0) {
                int n = ans.size();
                if (n == 0) {
                    ans.add(level);
                }else {
                    for (int i = 0; i < n; i++) {
                        List one = ans.get(i);
                        List<Integer> l = new ArrayList<>();
                        l.addAll(one);
                        l.addAll(level);
                        ans.add(l);
                        l = new ArrayList<>();
                        l.addAll(one);
                        for (int j = level.size() - 1; j >= 0; j--) {
                            l.add(level.get(j));
                        }
                        ans.add(l);
                    }
                    for (int i = 0; i < n; i++) {
                        ans.remove(0);
                    }
                }
                num = deque.size();
                level = new ArrayList<>();
            }
        }
        return ans;
    }
}
