import java.util.ArrayList;
import java.util.List;

public class a590postorder {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static void main(String[] args) {

    }

    public static List<Integer> ans;

    public List<Integer> postorder(Node root) {
        ans = new ArrayList<Integer>();
        tail(root);
        return ans;
    }

    public static void tail(Node root) {
        if (root == null) return;
        for (Node child : root.children) {
            tail(child);
        }
        ans.add(root.val);
    }
}
