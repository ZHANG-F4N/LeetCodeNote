import java.util.ArrayList;
import java.util.List;

public class a589preorder {
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

    ;

    public static void main(String[] args) {

    }

    public static List<Integer> ans;

    public static List<Integer> preorder(Node root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    public static void dfs(Node root) {
        if (root == null) return;
        ans.add(root.val);
        for (Node en : root.children) {
            dfs(en);
        }
    }
}
