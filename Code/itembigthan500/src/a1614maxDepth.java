import java.awt.event.MouseAdapter;
import java.util.Stack;

public class a1614maxDepth {
    public static void main(String[] args) {
        System.out.println(maxDepth("1+(2*3)/(2-1)"));
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
    }

    public static int maxDepth(String s) {

        int max = 0;
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') max++;
            else if (c == ')') max--;
            ans = Math.max(ans, max);
        }
        return ans;


    }
}
