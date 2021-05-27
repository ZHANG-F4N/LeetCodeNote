public class a104maxDepth {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Integer[] nums = {3, 9, 20, null, null, 15, 7};
        root = root.Build(nums);
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        return DFS(root);
    }
    public static int DFS(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(DFS(root.left),DFS(root.right))+1;
    }
}
