import java.util.Arrays;
import java.util.Stack;

public class a38dailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temperatures)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int N = temperatures.length;
        int[] ans = new int[N];
        stack.push(0);
        for (int i = 1; i < N; i++) {
            int top = temperatures[stack.peek()];
            int temp = temperatures[i];
            while (top < temp) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
                if (stack.empty()) {
                    break;
                }
                top = temperatures[stack.peek()];
            }
            stack.push(i);
        }
        return ans;
    }
}
