import java.util.ArrayDeque;

public class a116connect {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7};
        Node root = new Node();
        root = root.Build(nums);
        connect(root);
    }

    public static Node connect(Node root) {

        if (root == null){
            return root;
        }
        //递归
        //因为是完全二叉树,只判左子树就ok
        if (root.left!=null){
            root.left.next = root.right;
            root.right.next = (root.next == null ? null:root.next.left);
            connect(root.left);
            connect(root.right);
        }
        /*
        迭代做法
        ArrayDeque<Node> nodeDeque = new ArrayDeque<>();
        nodeDeque.offer(root);
        while(!nodeDeque.isEmpty()){
            int size = nodeDeque.size();
            for (int i = 0; i < size; i++) {
                Node temp = nodeDeque.poll();
                if (nodeDeque.isEmpty() || i == size-1){
                    temp.next = null;
                }else {
                    temp.next = nodeDeque.peek();
                }
                if (temp.left != null){
                    nodeDeque.offer(temp.left);
                }
                if (temp.right != null){
                    nodeDeque.offer(temp.right);
                }

            }
        }*/
        return root;
    }
}
