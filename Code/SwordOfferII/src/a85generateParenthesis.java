import java.util.ArrayList;
import java.util.List;

public class a85generateParenthesis {
    public static void main(String[] args) {
        generateParenthesis(3);
        generateParenthesis(2);
        generateParenthesis(1);
    }


    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        traceback(ans, new StringBuilder(""), n, n, 0, 0);
        return ans;
    }

    public static void traceback(List<String> ans, StringBuilder instance, int restl,
                                 int restr, int left, int right) {
        if (restl == 0 && restr == 0) {
            ans.add(instance.toString());
            return;
        }
        if (restl == 0 && left > right) {
            traceback(ans, instance.append(')'), restl, restr - 1, left, right + 1);
            instance.deleteCharAt(instance.length() - 1);
        }
        if (restl != 0 && left == right) {
            traceback(ans, instance.append('('), restl - 1, restr, left + 1, right);
            instance.deleteCharAt(instance.length() - 1);
        }
        if (restl != 0 && left > right) {
            traceback(ans, instance.append('('), restl - 1, restr, left + 1, right);
            instance.deleteCharAt(instance.length() - 1);
            traceback(ans, instance.append(')'), restl, restr - 1, left, right + 1);
            instance.deleteCharAt(instance.length() - 1);
        }
    }
}
