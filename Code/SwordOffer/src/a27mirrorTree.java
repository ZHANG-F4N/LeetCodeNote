public class a27mirrorTree {
    public static void main(String[] args) {
        Integer[] root = {4, 2, 7, 1, 3, 6, 9};


        TreeNode tree = new TreeNode().Build(root);

        mirrorTree(tree);
        System.out.println(1);


    }

    public static TreeNode mirrorTree(TreeNode root) {

        swap(root);
        return root;

    }

    public static void swap(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        swap(root.left);
        swap(root.right);
    }
}
