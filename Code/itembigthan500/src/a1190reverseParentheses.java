import java.time.chrono.IsoChronology;
import java.util.HashMap;
import java.util.Stack;

public class a1190reverseParentheses {
    public static void main(String[] args) {
        System.out.println(reverseParentheses2("(ed(et(oc))el)"));
    }

    public static String reverseParentheses(String s) {
        if (s.length() == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(ch);
                continue;
            }
            if (ch == ')') {
                StringBuilder temp = new StringBuilder();
                while (!stack.empty()) {
                    if (stack.peek() == '(') {

                        stack.pop();
                        break;
                    }
                    temp.append(stack.pop());
                }
                for (int j = 0; j < temp.length(); j++) {
                    stack.push(temp.charAt(j));
                }
                continue;
            }
            stack.push(ch);
        }
        while (!stack.empty()) {
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }


    public static String reverseParentheses2(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[s.length()];
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                pair[i] = stack.peek();
                pair[stack.peek()] = i;
                stack.pop();
//                hashMap.put(stack.pop(), i);
            }
        }

        StringBuilder ans = new StringBuilder();
        int index = 0;
        int direct = 1;

        while (index < s.length()) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pair[index];
                direct = -direct;
            } else {
                ans.append(s.charAt(index));
            }
            index += direct;
        }
        return ans.toString();
    }

}
