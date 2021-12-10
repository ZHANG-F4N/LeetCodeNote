public class a0412pathSum {
    public static void main(String[] args) {
//        Integer[] leaf = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        Integer[] leaf = {1, 2, null, 3, null, 4, null, 5};
//        Integer[] leaf = {1, null, 2, null, 3, null, 4, null, 5};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(pathSum(root, 6));
    }

    //
    public static int ans = 0;

    public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        DFS(root, 0, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return ans;
    }

    public static void DFS(TreeNode root, int pre, int tar) {
        if (root == null) return;
        if (pre + root.val == tar) ans++;
        DFS(root.left, pre + root.val, tar);
        DFS(root.right, pre + root.val, tar);
    }
}
