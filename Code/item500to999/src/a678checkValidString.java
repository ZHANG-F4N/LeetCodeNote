import java.util.ArrayDeque;

public class a678checkValidString {
    public static void main(String[] args) {
        System.out.println(checkValidString("(((((*)))**"));
        System.out.println(checkValidString("(*))"));
    }

    public static boolean checkValidString(String s) {
        if (s.equals("")) return true;
        char[] chars = s.toCharArray();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> starDeque = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') deque.push(i);
            else if (chars[i] == '*') starDeque.push(i);
            else if (chars[i] == ')') {
                if (!deque.isEmpty()) deque.pop();
                else if (starDeque.isEmpty()) return false;
                else if (!starDeque.isEmpty()) starDeque.pop();
            }
        }
        // 左括号可能不为空,和星匹配
        while (!deque.isEmpty() && !starDeque.isEmpty()) {
            int l = deque.pop();
            int r = starDeque.pop();
            if (r < l) return false;
        }
        return deque.isEmpty();
    }

    public static boolean checkValidStringDP(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        boolean dp[][] = new boolean[n + 1][n + 1];
        dp[0][0] = true;
        // dp[i][j]为考虑前 i 个字符（字符下标从 1 开始），能否与 j 个右括号形成合法括号序列。







        return dp[n][0];

    }

}
