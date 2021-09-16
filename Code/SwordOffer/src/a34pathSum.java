import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class a34pathSum {
    public static void main(String[] args) {
//        Integer[] leaf = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        Integer[] leaf = {1, -2, -3, 1, 3, -2, null, -1};
        TreeNode tree = new TreeNode().Build(leaf);

        ArrayList<Integer> integers = new ArrayList<>();
        List<List<Integer>> ans = pathSum(tree, -1);

    }

    public static List<List<Integer>> pathSum(TreeNode root, int target) {


        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        DFS(root, list, target, 0, ans);
        return ans;
    }


    public static void DFS(TreeNode root, List<Integer> list, int tar, int sum, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
//        if (root.val + sum == tar && (root.left != null || root.right != null)) {
//            return;
//        }

        if (root.val + sum == tar && root.left == null && root.right == null) {
            list.add(root.val);
            List<Integer> temp = new ArrayList<Integer>();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                temp.add(it.next());
            }
            ans.add(temp);
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        DFS(root.left, list, tar, sum + root.val, ans);
        DFS(root.right, list, tar, sum + root.val, ans);
        list.remove(list.size() - 1);

    }
}
