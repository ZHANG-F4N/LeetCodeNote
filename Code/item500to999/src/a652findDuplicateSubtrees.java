import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a652findDuplicateSubtrees {
    public static void main(String[] args) {
        Integer[] leaf = new Integer[]{1, 2, 3, 4, null, 2, 4, null, null, 4};
        TreeNode root = new TreeNode().Build(leaf);
        System.out.println(findDuplicateSubtrees(root));


    }

    static HashMap<String, Integer> map = new HashMap<>();
    static List<TreeNode> ans = new ArrayList<TreeNode>();

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    public static String dfs(TreeNode root) {
        if (root == null) return " ";

        StringBuilder builder = new StringBuilder();
        builder.append(root.val).append("_");
        builder.append(dfs(root.left)).append(dfs(root.right));
        String key = builder.toString();
        map.put(key, map.getOrDefault(key, 0) + 1);
        if (map.get(key) == 2) ans.add(root);
        return key;
    }
}
