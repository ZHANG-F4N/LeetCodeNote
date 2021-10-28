import java.util.ArrayDeque;
import java.util.Deque;

public class a297Codec {
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        Integer[] leaf = {1, 2, 3, null, null, 4, 5};
        TreeNode root = new TreeNode().Build(leaf);
        TreeNode ans = deser.deserialize(ser.serialize(root));
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        StringBuilder ans = new StringBuilder();
        deque.offer(root);
        ans.append(root.val);
        ans.append(',');
        while (!deque.isEmpty()) {
            TreeNode temp = deque.poll();
            if (temp.left == null) {
                ans.append("null");
            } else {
                ans.append(temp.left.val);
                deque.offer(temp.left);
            }
            ans.append(',');
            if (temp.right == null) {
                ans.append("null");
            } else {
                ans.append(temp.right.val);
                deque.offer(temp.right);
            }
            ans.append(',');
        }
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") {
            return null;
        }
        String[] leaf = data.split(",");
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(leaf[0]));
        deque.offer(root);
        int index = 1;
        while (!deque.isEmpty()) {
            TreeNode temp = deque.poll();
            if (leaf[index].equals("null") ) {
                temp.left = null;
            } else {
                temp.left = new TreeNode(Integer.parseInt(leaf[index]));
                deque.offer(temp.left);
            }

            index++;
            if (leaf[index].equals("null")) {
                temp.right = null;
            } else {
                temp.right = new TreeNode(Integer.parseInt(leaf[index]));
                deque.offer(temp.right);
            }
            index++;
        }

        return root;
    }
}