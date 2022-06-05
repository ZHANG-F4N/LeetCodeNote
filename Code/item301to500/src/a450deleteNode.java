public class a450deleteNode {
    public static void main(String[] args) {

    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 左右都为空时直接删除即可。
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode t = root.left;
            while (t.right != null) t = t.right;
            t.right = root.right;
            return root.left;// 重点理解
        } else if (root.val > key) root.left = deleteNode(root.left, key);
        else root.right = deleteNode(root.right, key);
        return root;
    }
}