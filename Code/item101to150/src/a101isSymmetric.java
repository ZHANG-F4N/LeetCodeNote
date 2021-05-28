import com.sun.source.tree.Tree;

import java.util.*;

public class a101isSymmetric {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        Integer[] nums = {1, 2, 2, 3, 4, 4, 3};
        root = root.Build(nums);
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return check(root,root);
    }
    public static boolean check(TreeNode left,TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        return left.val == right.val && check(left.left,right.right) && check(left.right,right.left);
    }
}
