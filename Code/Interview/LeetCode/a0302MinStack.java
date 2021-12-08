import java.util.Stack;

public class a0302MinStack {
    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> mStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            mStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (mStack.empty()) {
                mStack.push(x);
            } else {
                mStack.push(Math.min(x, mStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            mStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return mStack.peek();

        }
    }
}
