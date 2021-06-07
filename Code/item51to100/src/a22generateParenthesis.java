import java.util.ArrayList;
import java.util.List;

public class a22generateParenthesis {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(generateParenthesis(n));
    }

    //DFS
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        backtrack(ans, str, 0, 0, n);
        return ans;
    }
    //ans   ---     结果集
    //str   ---     零时存储中间结果 StringBuilder可变长,线程不安全,最快
    //left  ---     左括号个数
    //right ---     右括号个数
    //n     ---     总括号对数
    public static void backtrack(List<String> ans, StringBuilder str, int left, int right, int n) {
        if (str.length() >= n << 1) {
            ans.add(str.toString());
            return;
        }
        if (left < n) {
            backtrack(ans, str.append('('), left + 1, right, n);
            str.deleteCharAt(str.length() - 1);
        }
        if (right < left) {
            backtrack(ans, str.append(')'), left, right + 1, n);
            str.deleteCharAt(str.length() - 1);
        }

    }
}
