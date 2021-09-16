public class a54kthLargest {
    public static void main(String[] args) {

//        Integer[] leaf = {5, 3, 6, 2, 4, null, null, 1};
        Integer[] leaf = {3,1,4,null,2};
        TreeNode tree = new TreeNode().Build(leaf);
        System.out.println(kthLargest(tree, 1));
    }

    static int num = 0;
    static int ans = 0;

    public static int kthLargest(TreeNode root, int k) {
        num = 0;
        ans = 0;
        midOrder(root, k);
        return ans;

    }

    public static void midOrder(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        midOrder(root.right, k);

        num++;
        if (num == k) {
            ans = root.val;
        }
        midOrder(root.left, k);

    }
}
