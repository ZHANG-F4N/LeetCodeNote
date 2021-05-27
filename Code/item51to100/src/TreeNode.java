import java.util.ArrayDeque;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode Build(Integer[] leaf) {
        if (leaf.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(leaf[0]);
        ArrayDeque<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.addLast(root);
        int index = 1;
        while (!treeNodes.isEmpty()) {
            TreeNode temp = treeNodes.pollFirst();
            if (index >= leaf.length) {
                return root;
            }
            if (leaf[index] != null ) {
                TreeNode leftNode = new TreeNode(leaf[index++]);
                temp.left = leftNode;
                treeNodes.addLast(leftNode);
            }else {
                index++;
                temp.left = null;
            }
            if (leaf[index] != null ) {
                TreeNode rightNode = new TreeNode(leaf[index++]);
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
