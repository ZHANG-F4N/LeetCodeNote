public class a230kthSmallest {
    public static void main(String[] args) {
        Integer[] nums = {3,1,4,null,2};
        TreeNode root = new TreeNode();
        root = root.Build(nums);
        System.out.println(kthSmallest(root, 1));
    }

    private static int count = 0;
    private static int ans = 0;
    public static int kthSmallest(TreeNode root, int k) {
        inOrderDFS(root,k);
        return ans;
    }

    public static void inOrderDFS(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrderDFS(root.left, k);
        count++;
        if (count == k){
            ans = root.val;
            return;
        }
        inOrderDFS(root.right,k);
    }
}
