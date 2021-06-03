import java.util.ArrayDeque;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
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
            if (leaf[index] != null ) {
                Node leftNode = new Node(leaf[index++]);
                temp.left = leftNode;
                treeNodes.addLast(leftNode);
            }else {
                index++;
                temp.left = null;
            }
            if (leaf[index] != null ) {
                Node rightNode = new Node(leaf[index++]);
                temp.right = rightNode;
                treeNodes.addLast(rightNode);
            }else {
                index++;
                temp.right = null;
            }
        }
        return root;
    }
}
