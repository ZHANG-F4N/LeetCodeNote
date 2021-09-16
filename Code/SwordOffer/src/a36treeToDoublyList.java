import java.util.ArrayDeque;

public class a36treeToDoublyList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        public Node Build(Integer[] leaf) {
            if (leaf.length == 0) {
                return null;
            }
            Node root = new Node(leaf[0]);
            ArrayDeque<Node> treeNodes = new ArrayDeque<>();
            treeNodes.addLast(root);
            int index = 1;
            while (!treeNodes.isEmpty()) {
                Node temp = treeNodes.pollFirst();
                if (index >= leaf.length) {
                    return root;
                }
                if (leaf[index] != null) {
                    Node leftNode = new Node(leaf[index++]);
                    temp.left = leftNode;
                    treeNodes.addLast(leftNode);
                } else {
                    index++;
                    temp.left = null;
                }
                if (index >= leaf.length) {
                    return root;
                }
                if (leaf[index] != null) {
                    Node rightNode = new Node(leaf[index++]);
                    temp.right = rightNode;
                    treeNodes.addLast(rightNode);
                } else {
                    index++;
                    temp.right = null;
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Integer[] leaf = {4, 2, 5, 1, 3, null, null};
        Node tree = new Node().Build(leaf);
        treeToDoublyList(tree);
    }

    static Node pre, head;

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        pre = null;
        head = null;
        midOrder(root);
        head.left = pre;
        pre.right = head;
        return head;

    }

    public static void midOrder(Node root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
//      1. 修改前一个节点的后 为当前节点
//      2. 修改当前节点的前 为前一个节点
//      3. 更新当前节点为前一个节点
        if (pre != null) {
            pre.right = root;
        } else {
            head = root;
        }
        root.left = pre;

        pre = root;
        midOrder(root.right);

    }
}
