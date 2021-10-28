public class a124maxPathSum {
    public static void main(String[] args) {
        Integer[] leaf = {-10, 9, 20, null, null, 15, 7};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        return 0;

    }
}
