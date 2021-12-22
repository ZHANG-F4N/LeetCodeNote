public class a437pathSum {
    public static void main(String[] args) {

//        Integer[] leaf = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        Integer[] leaf = {1, null, 2, null, 3, null, 4, null, 5};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(pathSum(root, 3));
    }


//    public static boolean flag = false;
//
//    public static boolean hasPathSum1(TreeNode root, int targetSum) {
//        flag = false;
//
//        DFS1(root, targetSum, 0);
//
//        return flag;
//    }
//
//    public static void DFS1(TreeNode root, int targetSum, int sum) {
//        if (root == null || flag == true) {
//            return;
//        }
//        sum += root.val;
//
//        if (root.left == null && root.right == null && sum == targetSum) {
//            flag = true;
//            return;
//        }
//        DFS1(root.left, targetSum, sum);
//        DFS1(root.right, targetSum, sum);
//    }

//    public static int ans = 0;

    // 返回 从 root 出发 的左右子树有多少个等于 target的路径
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = DFS(root, targetSum); // 从 root 出发的路径
        // 调用自己
        ans += pathSum(root.left, targetSum); // 从 root 的左子树出发的路径
        ans += pathSum(root.right, targetSum);// 从 root 的右子树出发的路径
        return ans;
    }

    // 判断当前节点有没有路径
    public static int DFS(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = root.val == targetSum ? 1 : 0;
        ret += DFS(root.left, targetSum - root.val);
        ret += DFS(root.right, targetSum - root.val);
        return ret;
    }

}
