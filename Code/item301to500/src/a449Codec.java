import java.util.ArrayDeque;
import java.util.Deque;

public class a449Codec {
    public static void main(String[] args) {
        Integer[] leaf = {};
//        Integer[] leaf = {3, 1, 2, null, null, 4, null};
        TreeNode root = new TreeNode().Build(leaf);

        Codec codec = new Codec();
        String a = codec.serialize(root);
        TreeNode ans = codec.deserialize(a);
        System.out.println(a);
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder ans = new StringBuilder();
            Deque<TreeNode> deque = new ArrayDeque<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                TreeNode temp = deque.pollFirst();
                if (temp.val == -1) {
                    ans.append("null");
                    ans.append(',');
                    continue;
                } else ans.append(temp.val);
                ans.append(',');
                if (temp.left != null) deque.addLast(temp.left);
                else deque.addLast(new TreeNode(-1));
                if (temp.right != null) deque.addLast(temp.right);
                else deque.addLast(new TreeNode(-1));
            }
            ans.deleteCharAt(ans.length() - 1);
            return ans.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("")) return null;
            String[] node = data.split(",");
            // 层次遍历建立二叉树
            TreeNode root = new TreeNode();
            ArrayDeque<TreeNode> deque = new ArrayDeque<>();
            root.val = Integer.parseInt(node[0]);
            deque.addFirst(root);
            for (int i = 1; i < node.length; i += 2) {
                TreeNode temp = deque.pollFirst();
                if (node[i].equals("null")) temp.left = null;
                else temp.left = new TreeNode(Integer.parseInt(node[i]));
                if (node[i + 1].equals("null")) temp.right = null;
                else temp.right = new TreeNode(Integer.parseInt(node[i + 1]));
                if (temp.left != null) deque.addLast(temp.left);
                if (temp.right != null) deque.addLast(temp.right);
            }
            return root;
        }
    }
}
