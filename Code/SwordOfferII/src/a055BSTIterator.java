import java.util.ArrayList;
import java.util.List;

public class a055BSTIterator {
    public static void main(String[] args) {
        
    }
}

class BSTIterator {
    static int next;
    TreeNode root;
    // 中序遍历后有序的节点数组
    List<Integer> array;

    public BSTIterator(TreeNode root) {
        next = 0;
        this.root = root;
        array = new ArrayList<>();
        midOrder(root);
    }

    public int next() {
        return array.get(next++);
    }

    public boolean hasNext() {
        return next < array.size();
    }

    public void midOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        array.add(root.val);
        midOrder(root.right);
    }
}
