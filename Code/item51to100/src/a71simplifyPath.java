import java.util.Iterator;
import java.util.Stack;

public class a71simplifyPath {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
        System.out.println(simplifyPath("/home//foo/"));
    }

    public static String simplifyPath(String path) {
        StringBuilder ans = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] dir = path.split("/");
        for (int i = 0; i < dir.length; i++) {
            if (dir[i].equals("") || dir[i].equals(".")) continue;
            else if (!stack.isEmpty() && dir[i].equals("..")) stack.pop();
            else if (stack.isEmpty() && dir[i].equals("..")) continue;
            else stack.push(dir[i]);
        }
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            ans.append("/");
            ans.append(iterator.next());
        }
        if (ans.length()==0)ans.append("/");
        return ans.toString();
    }
}
