public class a563findTilt {
    public static void main(String[] args) {
//        Integer[] leaf = new Integer[]{4, 2, 9, 3, 5, null, 7};
        Integer[] leaf = new Integer[]{21, 7, 14, 1, 1, 2, 2, 3, 3};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(findTilt(root));
    }

    public static int ans = 0;

    public static int findTilt(TreeNode root) {
        ans = 0;
        sum(root);
        return ans;
    }


    public static int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        if (root.left != null) {
            left = root.left.val + sum(root.left);
        }
        int right = 0;
        if (root.right != null) {
            right = root.right.val + sum(root.right);
        }

        int val = left + right;
        root.val = Math.abs(left - right);
        ans += root.val;
        return val;
    }


}
