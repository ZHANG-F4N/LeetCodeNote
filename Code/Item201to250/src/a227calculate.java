import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class a227calculate {
    public static void main(String[] args) {
        String s = "1-1+1";
        System.out.println(calculate(s));
    }

    public static int calculate(String s) {
        ArrayList<String> list = new ArrayList<>();
        char[] sChars = s.toCharArray();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] == ' ') {
                continue;
            }
            if (sChars[i] == '+' || sChars[i] == '-' || sChars[i] == '/' || sChars[i] == '*') {
                list.add(temp.toString());
                list.add(String.valueOf(sChars[i]));
                temp = new StringBuilder();
            } else {
                temp.append(sChars[i]);
            }
        }
        list.add(temp.toString());
        int ans = 0;

        Stack<String> opStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String topNum = list.get(i);
            if (topNum.equals("+") || topNum.equals("-")) {
                opStack.push(topNum);
            } else if (topNum.equals("/") || topNum.equals("*")) {
                int left = Integer.valueOf(numStack.pop());
                int right = Integer.valueOf(list.get(i + 1));
                i += 2;
                if (topNum.equals("/")) {
                    numStack.push(left / right);
                }
                if (topNum.equals("*")) {
                    numStack.push(left * right);
                }
            } else {
                numStack.push(Integer.valueOf(topNum));
            }
        }
        while (!opStack.empty()) {
            int right = Integer.valueOf(numStack.pop());
            int left = Integer.valueOf(numStack.pop());
            String op = opStack.pop();
            if (op.equals("+")) {
                numStack.push(left + right);
            }
            if (op.equals("-")) {
                numStack.push(left - right);
            }
        }
        return numStack.pop();
    }

}
