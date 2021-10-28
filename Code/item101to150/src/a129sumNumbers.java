public class a129sumNumbers {
    public static void main(String[] args) {
        Integer[] leaf = {4, 9, 0, 5, 1};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(sumNumbers(root));
    }

    private static int ans;

    public static int sumNumbers(TreeNode root) {
        ans = 0;
        DFS(root, 0);
        return ans;
    }

    public static void DFS(TreeNode root, int num) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans += num * 10 + root.val;
            return;
        }
        DFS(root.left, num*10+root.val);
        DFS(root.right, num*10+root.val);
    }
}
