public class a0410checkSubTree {
    public static void main(String[] args) {
        Integer[] leaf = {1, 2, 3};

    }

    public static boolean checkSubTree(TreeNode t1, TreeNode t2) {

//        if (t1 == null && t2 == null) {
//            return true;
//        } else if (t1 == null && t2 != null) {
//            return false;
//        } else if (t2 == null && t1 != null) {
//            return false;
//        } else if (t1.val == t2.val) {
//            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
//        } else {
//            return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
//        }

        if (t1 == null) return false;
        if (t2 == null) return true;
        return DFS(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);

    }


    public static boolean DFS(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if ((t1 == null && t2 != null) || (t2 == null && t1 != null)) {
            return false;
        } else {
            return t1.val == t2.val && DFS(t1.left, t2.left) && DFS(t1.right, t2.right);
        }
    }
}
