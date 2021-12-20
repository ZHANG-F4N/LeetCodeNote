import java.util.ArrayList;

public class a0405isValidBST {
    public static void main(String[] args) {
        Integer[] leaf = {5, 1, 4, null, null, 3, 6};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
//        方法一
//        ArrayList<Integer> arr = new ArrayList<>();
//        inOrder(root, arr);
//        if (arr.size() <= 1) return true;
//        for (int i = 0; i < arr.size() - 1; i++) {
//            if (arr.get(i) > arr.get(i + 1)) return false;
//        }
//        return true;

//        方法二
        return check(root, null, null);
    }

    public static void inOrder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return;
        inOrder(root.left, arr);
        arr.add(root.val);
        inOrder(root.right, arr);
    }

    public static boolean check(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        int val = root.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;
        if (!check(root.left, lower, val)) return false;
        if (!check(root.right, val, upper)) return false;
        return true;
    }

}
