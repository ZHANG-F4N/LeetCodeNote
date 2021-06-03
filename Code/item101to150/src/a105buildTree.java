import java.util.HashMap;

public class a105buildTree {
    public static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        buildTree(preOrder, inOrder);
    }

    private static HashMap<Integer, Integer> indexMap;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root;
        int n = preorder.length;
        //树中没有重复元素
        indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        root = myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        return root;
    }

    public static TreeNode myBuildTree(int[] preorder, int[] inorder, int pleft, int pright, int ileft, int iright) {
        if (pleft > pright) {
            return null;
        }
        if (ileft > iright) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[pleft]);

        int rootIndex = indexMap.get(preorder[pleft]);

        int leftSize = rootIndex - ileft;


        treeNode.left = myBuildTree(preorder, inorder, pleft + 1, pleft + leftSize, ileft, rootIndex - 1);
        treeNode.right = myBuildTree(preorder, inorder, pleft + leftSize + 1, pright, rootIndex + 1, iright);

        return treeNode;
    }
}
