import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class a32levelOrder {
    public static void main(String[] args) {
        Integer[] node = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = new TreeNode().Build(node);
        System.out.println(Arrays.toString(levelOrder(tree)));
    }

    public static int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<Integer> ansT = new ArrayList<>();
        Queue<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            TreeNode temp = list.poll();
            ansT.add(temp.val);
            if (temp.left != null) {
                list.offer(temp.left);
            }
            if (temp.right != null) {
                list.offer(temp.right);
            }
        }
        int[] ans = new int[ansT.size()];
        for (int i = 0; i < ansT.size(); i++) {
            ans[i] = ansT.get(i);
        }
        return ans;
    }
}
