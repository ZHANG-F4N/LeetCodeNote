import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class a236lowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode().Build(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode p = DFS(root, 5);
        TreeNode q = DFS(root, 4);
        TreeNode ans = lowestCommonAncestor3(root, p, q);
        System.out.println(ans.val);
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static void newDFS(TreeNode father, TreeNode son, HashMap hashMap) {
        if (son == null) {
            return;
        }
        hashMap.put(son, father);
        newDFS(son, son.left, hashMap);
        newDFS(son, son.right, hashMap);
    }

    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> hashMap = new HashMap<>();
        newDFS(root, root.left, hashMap);
        newDFS(root, root.right, hashMap);

        ArrayList<TreeNode> ptreeNodes = new ArrayList<>();

        while (hashMap.containsKey(p)) {
            ptreeNodes.add(p);
            p = hashMap.get(p);
        }
        ptreeNodes.add(p);
        ArrayList<TreeNode> qtreeNodes = new ArrayList<>();
        while (hashMap.containsKey(q)) {
            qtreeNodes.add(q);
            q = hashMap.get(q);
        }
        qtreeNodes.add(q);

        int distance = Math.abs(qtreeNodes.size() - ptreeNodes.size());


        ArrayList<TreeNode> maxTreeNodes = null;
        ArrayList<TreeNode> minTreeNodes = null;


        if (qtreeNodes.size() > ptreeNodes.size()) {
            maxTreeNodes = qtreeNodes;
            minTreeNodes = ptreeNodes;
        } else {
            maxTreeNodes = ptreeNodes;
            minTreeNodes = qtreeNodes;
        }


        for (int i = 0; i < (maxTreeNodes.size() - distance); i++) {
            int longIndex = distance + i;
            if (minTreeNodes.get(i) == maxTreeNodes.get(longIndex)){
                return minTreeNodes.get(i);
            }
        }
        System.out.println(distance);


        return null;


    }


    public static TreeNode DFS(TreeNode root, int temp) {
        if (root == null) {
            return null;
        }
        if (root.val == temp) {
            return root;
        } else {
            TreeNode ans = null;
            ans = DFS(root.left, temp);
            if (ans != null) {
                return ans;
            }
            ans = DFS(root.right, temp);
            if (ans != null) {
                return ans;
            }
        }
        return null;
    }

    //简洁版
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left != null && right != null) {
            return root;

        }
        return left == null ? right : left;
    }

    public static TreeNode ans;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        judgeContains(root, p, q);
        return ans;
    }

    public static boolean judgeContains(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = judgeContains(root.left, p, q);
        boolean right = judgeContains(root.right, p, q);
        if ((left && right) || (root == q || root == p) && (left || right)) {
            ans = root;
        }
        return (left || right) || (root == q || root == p);

    }

    //计算一个节点的深度
    public static int nodeDeep(TreeNode root, TreeNode p, int deep) {
        if (root == null) {
            return 0;
        }
        if (root == p) {
            return deep;
        }
        int left = 0;
        int right = 0;
        left = nodeDeep(root.left, p, deep + 1);
        right = nodeDeep(root.right, p, deep + 1);

        return left > right ? left : right;

    }
}
