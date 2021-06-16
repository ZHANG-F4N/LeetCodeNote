import java.util.LinkedList;
import java.util.Stack;

public class a150evalRPN {
    public static void main(String[] args) {
        String tokens[] = {"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i].equals("+") ) {
                int rightNum = stack.pop();
                int leftNum = stack.pop();
                stack.push(leftNum + rightNum);
                continue;
            }
            if (tokens[i].equals("-")) {
                int rightNum = stack.pop();
                int leftNum = stack.pop();
                stack.push(leftNum - rightNum);
                continue;
            }
            if (tokens[i].equals("*")) {
                int rightNum = stack.pop();
                int leftNum = stack.pop();
                stack.push(leftNum * rightNum);
                continue;
            }
            if (tokens[i].equals("/")) {
                int rightNum = stack.pop();
                int leftNum = stack.pop();
                stack.push(leftNum / rightNum);
                continue;
            }
            stack.push(Integer.valueOf(tokens[i]));
        }
        return stack.pop();
    }
}
