import java.util.*;

public class a32levelOrder {
    public static void main(String[] args) {
        Integer[] node = {3, 9, 20, null, null, 15, 7};
        TreeNode tree = new TreeNode().Build(node);
        System.out.println(levelOrder2(tree).toString());

    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            List<Integer> integers = new ArrayList<>();
            for (int i = list.size(); i > 0; i--) {
                TreeNode temp = list.poll();
                integers.add(temp.val);
                if (temp.left != null) {
                    list.offer(temp.left);
                }
                if (temp.right != null) {
                    list.offer(temp.right);
                }
            }
            ans.add(integers);
        }
        return ans;
    }

    //请实现一个函数按照之字形顺序打印二叉树，
    // 即第一行按照从左到右的顺序打印，
    // 第二层按照从右到左的顺序打印，
    // 第三行再按照从左到右的顺序打印，其他行以此类推。
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> list = new LinkedList<>();
        int level = 1;
        list.offer(root);
        while (!list.isEmpty()) {
            LinkedList<Integer> integers = new LinkedList<>();

            for (int i = list.size(); i > 0; i--) {
                TreeNode temp = list.poll();
                if (level % 2 == 0) {
                    integers.offerFirst(temp.val);
                } else {
                    integers.offerLast(temp.val);
                }
                if (temp.left != null) {
                    list.offer(temp.left);
                }
                if (temp.right != null) {
                    list.offer(temp.right);
                }
            }
            level++;
            ans.add(integers);
        }
        return ans;
    }
}
