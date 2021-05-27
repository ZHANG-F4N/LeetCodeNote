public class a98isValidBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Integer[] nums = {5,1,4,null,null,3,6};
        root = root.Build(nums);
        System.out.println(isValidBST(root));
    }
    public static boolean isValidBST(TreeNode root) {
        return  false;
    }
}
