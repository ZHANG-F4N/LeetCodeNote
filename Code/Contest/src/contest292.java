import java.util.ArrayList;
import java.util.Stack;

public class contest292 {
    public static void main(String[] args) {
//        System.out.println(largestGoodInteger("6777133339"));
        System.out.println(hasValidPath(new char[][]{{'(', '(', '('}, {')', '(', ')'}, {'(', '(', ')'}, {
                '(', '(', ')'}}));
    }

    //q1
    public static String largestGoodInteger(String num) {
        int length = num.length();
        char[] chars = num.toCharArray();
        String ans = "";
        for (int i = 0; i < length - 2; i++) {
            if (chars[i] == chars[i + 1] && chars[i + 1] == chars[i + 2]) {
                if (ans.equals("")) ans = "" + chars[i] + chars[i + 1] + chars[i + 2];
                else {
                    if (ans.charAt(0) < chars[i]) ans = "" + chars[i] + chars[i + 1] + chars[i + 2];
                }
            }
        }
        return ans;
    }

    public static int ans;


    // Q2
    public static int averageOfSubtree(TreeNode root) {
        ans = 0;
        backOrder(root);
        return ans;
    }

    public static int[] backOrder(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = backOrder(root.left);
        int[] r = backOrder(root.right);
        int num = 1 + l[0] + r[0];
        int sum = root.val + l[1] + r[1];
        if (sum / num == root.val) ans++;
        return new int[]{num, sum};
    }

    public static int countTexts(String pressedKeys) {
        int n = pressedKeys.length();

        char[] chars = pressedKeys.toCharArray();

        int[] dp = new int[n];
        dp[0] = (chars[0] == '7' || chars[0] == 9) ? 4 : 3;
        for (int i = 1; i < n; i++) {
            if ((chars[i] == '7' || chars[9] == 9)) ;
        }

        return dp[n - 1];

    }

    public static boolean flag;

    public static boolean hasValidPath(char[][] grid) {
        if (grid[0][0] == ')') return false;
        flag = false;
        ArrayList<Character> stack = new ArrayList<>();
        dfs(grid, 0, 0, stack, 0, 0);
        return flag;

    }

    // 有括号大于左括号,false
    public static void dfs(char[][] grid, int i, int j, ArrayList<Character> stack, int l, int r) {
        if (flag) return;
        int n = grid.length;
        int m = grid[0].length;
        if (i == n && j == m) {
            ArrayList<Character> temp = new ArrayList<>(stack);
            Stack<Character> right = new Stack<>();
            for (int k = temp.size() - 1; k >= 0; k--) {
                if (temp.get(k) == ')') {
                    right.push(stack.get(k));
                } else {
                    if (right.empty()) return;
                    right.pop();
                }
            }
            if (right.empty()) flag = true;
            return;
        }

        // 如果现在就不满足,剪枝
        if (l < r) return;

        if (i + 1 < n) {
            stack.add(grid[i + 1][j]);
            if (grid[i + 1][j] == '(') dfs(grid, i + 1, j, stack, l + 1, r);
            if (grid[i + 1][j] == ')') dfs(grid, i + 1, j, stack, l, r + 1);
            stack.remove(stack.size() - 1);
        }
        if (j + 1 < m) {
            stack.add(grid[i][j + 1]);
            if (grid[i + 1][j] == '(') dfs(grid, i, j + 1, stack, l + 1, r);
            if (grid[i + 1][j] == ')') dfs(grid, i, j + 1, stack, l, r + 1);
            stack.remove(stack.size() - 1);
        }
    }

}
