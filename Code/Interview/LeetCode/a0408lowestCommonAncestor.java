import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class a0408lowestCommonAncestor {
    public static void main(String[] args) {
        Integer[] leaf = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
//        Integer[] leaf = {1, 2, 3, null, 4};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(lowestCommonAncestor2(root, root.left, root.left.right).val);
    }

    public static TreeNode ans = null;

    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        find(root, p, q);
        return ans;
    }

    public static boolean find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean leftHasSon = find(root.left, p, q);
        boolean rightHasSon = find(root.right, p, q);
        if (leftHasSon && rightHasSon || ((root == p || root == q) && (leftHasSon || rightHasSon))) {
            ans = root;
        }
        return leftHasSon || rightHasSon || root == p || root == q;
    }


    public static boolean flag;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> dequeP = new ArrayDeque<>();
        Deque<TreeNode> dequeQ = new ArrayDeque<>();
        flag = false;
        DFS(root, p, new ArrayDeque<>(), dequeP);
        dequeP.addLast(p);
        flag = false;
        DFS(root, q, new ArrayDeque<>(), dequeQ);
        dequeQ.addLast(q);
        TreeNode ans = dequeP.peekFirst();
        while (!dequeP.isEmpty() && !dequeQ.isEmpty() && dequeP.peekFirst() == dequeQ.peekFirst()) {
            ans = dequeP.peekFirst();
            dequeP.pollFirst();
            dequeQ.pollFirst();
        }
//        if (dequeP.isEmpty() && !dequeQ.isEmpty()) ans = dequeQ.peekFirst();
//        if (dequeQ.isEmpty() && !dequeP.isEmpty()) ans = dequeP.peekFirst();
        return ans;
    }

    public static void DFS(TreeNode root, TreeNode tar, Deque<TreeNode> path,
                           Deque<TreeNode> ans) {
        if (root == null || flag) return;
        if (root == tar) {
            flag = true;
            Iterator<TreeNode> it = path.iterator();
            while (it.hasNext()) {
                ans.offerLast(it.next());
            }
            return;
        }
        path.offerLast(root);
        DFS(root.left, tar, path, ans);
        DFS(root.right, tar, path, ans);
        path.pollLast();
    }

}
