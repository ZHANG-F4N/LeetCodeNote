public class a26isSubStructure {
    public static void main(String[] args) {
        Integer[] A = {3, 4, 5, 1, 2};
        Integer[] B = {4, 1};

        TreeNode treeA = new TreeNode().Build(A);
        TreeNode treeB = new TreeNode().Build(B);
        TreeNode treeC = new TreeNode().Build(B);
        System.out.println(isSubStructure(treeA, treeB));
        System.out.println(recur(treeA, treeC));


    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return recur(A, B) || (isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public static boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        }

        if (A.val != B.val) {
            return false;
        } else {
            return recur(A.left, B.left) && recur(A.right, B.right);
        }
    }
}
