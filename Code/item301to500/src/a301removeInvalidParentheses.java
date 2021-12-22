import javax.naming.spi.DirObjectFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a301removeInvalidParentheses {
    public static void main(String[] args) {
        removeInvalidParentheses(")(");
    }

    private static char[] charArray;

    public static List<String> removeInvalidParentheses(String s) {

        //DFS + 剪枝 统计最小数量
        charArray = s.toCharArray();

        Set<String> list = new HashSet<>();
        int lCount = 0;
        int rCount = 0;
        // 统计左右括号有多少个不合法
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '(') {
                lCount++;
            } else if (charArray[i] == ')') {
                if (lCount > 0) {
                    //抵消一个
                    lCount--;
                } else {
                    //空了的话,右括号++
                    rCount++;
                }
            }
        }
         backtrace(list, new StringBuilder(""), charArray, 0, lCount, rCount, 0, 0);

        return new ArrayList<String>(list);

    }

    //回溯
    public static void backtrace(Set<String> ans, StringBuilder string, char[] str, int index, int lCount, int rCount, int l, int r) {
        if (lCount == 0 && rCount == 0 && index == str.length) {
            ans.add(string.toString());
            return;
        }

        if (index >= str.length) {
            return;
        }
        //删除当前 ( 括号
        if (str[index] == '(' && lCount > 0) {
            backtrace(ans, string, str, index + 1, lCount - 1, rCount, l, r);
        }
        // 删除当前 )
        if (str[index] == ')' && rCount > 0) {
            backtrace(ans, string, str, index + 1, lCount, rCount - 1, l, r);
        }
        // 保留当前
        string.append(str[index]);
        if (str[index] != '(' && str[index] != ')') {
            backtrace(ans, string, str, index + 1, lCount, rCount, l, r);
        } else if (str[index] == '(') {
            backtrace(ans, string, str, index + 1, lCount, rCount, l + 1, r);
        } else if (l >r) {
            backtrace(ans, string, str, index + 1, lCount, rCount, l, r + 1);
        }

        string.deleteCharAt(string.length() - 1);
    }
}
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, '(', ')', 0, 0);
        return res;
    }

    private void dfs(String s, List<String> res, char opening, char closing, int i, int lastRemoved) {
        int count = 0;
        // 从左向右扫描,找到第一个不合法的有括号
        while (i < s.length() && count >= 0) {
            if (s.charAt(i) == opening) {
                count++;
            } else if (s.charAt(i) == closing) {
                count--;
            }
            i++;
        }
        // 如果是右括号delete it and dfs
        if (count < 0) { // find one illegal closing, delete it and dfs
            for (int j = lastRemoved; j < i; j++) {
                if (s.charAt(j) == closing && (j == 0 || s.charAt(j - 1) != closing)) { // prevent duplicate
                    dfs(s.substring(0, j) + s.substring(j + 1), res, opening, closing, i - 1, j);
                }
            }
            // 如果左括号多了  反转
        } else if (count > 0) { // more openings needs redo from right to left to remove
            dfs(new StringBuilder(s).reverse().toString(), res, closing, opening, 0, 0);
        } else { // balance, add to result
            if (opening == '(') {
                res.add(s);
            } else {
                res.add(new StringBuilder(s).reverse().toString());
            }
        }
    }
}
