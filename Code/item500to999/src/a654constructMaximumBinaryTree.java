public class a654constructMaximumBinaryTree {
    public static void main(String[] args) {
        System.out.println(constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public static TreeNode build(int[] nums, int l, int r) {
        if (r < l) return null;

        if (l == r) {
            return new TreeNode(nums[l]);
        }

        int max = -1, idx = l - 1;
        for (int i = l; i <= r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        return new TreeNode(nums[idx], build(nums, l, idx - 1), build(nums, idx + 1, r));
    }
}
