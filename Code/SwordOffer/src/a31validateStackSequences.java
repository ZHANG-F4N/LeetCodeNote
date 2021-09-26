import java.util.Stack;

public class a31validateStackSequences {
    public static void main(String[] args) {
//        int[] pushed = {1, 0};
        int[] pushed = {1, 2, 3, 4, 5};
//        int[] popped = {4, 5, 3, 2, 1};
//        int[] popped = {1, 0};
        int[] popped = {4,3,5,1,2};
        System.out.println(validateStackSequences(pushed, popped));

    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();

        int pushIndex = 0;
        int popIndex = 0;
        while (pushIndex < pushed.length && popIndex < popped.length) {
            if (stack.empty()) {
                stack.push(pushed[pushIndex++]);
            }
            while (pushIndex < pushed.length && stack.peek() != popped[popIndex]) {
                stack.push(pushed[pushIndex++]);
            }
            while (popIndex < popped.length && !stack.empty() && stack.peek() == popped[popIndex]) {
                popIndex++;
                stack.pop();
            }
            if (stack.empty() && popIndex == popped.length) {
                return true;
            }
            if (pushIndex == pushed.length && !stack.empty()) {
                return false;
            }
        }
        return false;


    }
}
