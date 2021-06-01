public class a108sortedArrayToBST {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println(sortedArrayToBST(nums));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        //中序遍历递增

        return build(nums, 0, nums.length - 1);
    }

    public static TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right + left) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }
}
