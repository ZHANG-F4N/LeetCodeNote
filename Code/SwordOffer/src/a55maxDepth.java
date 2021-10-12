import javax.imageio.plugins.tiff.TIFFImageReadParam;

public class a55maxDepth {
    public static void main(String[] args) {
        Integer[] leaf = {3, 9, 20, null, null, 15, 7};

        TreeNode root = new TreeNode().Build(leaf);

        System.out.println(maxDepth(root));
    }

    static int maxAns = 0;

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxAns = 0;
        DFS(root, 1);
        return maxAns;
    }

    public static void DFS(TreeNode root, int Depth) {
        if (root == null) {
            return;
        }


        if (Depth > maxAns) {
            maxAns = Depth;
        }
        DFS(root.left, Depth + 1);
        DFS(root.right, Depth + 1);
    }


}
