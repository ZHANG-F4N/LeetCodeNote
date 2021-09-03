import java.util.Stack;

public class a30MinStack {
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.min();
     */
    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        int param_3 = obj.min();
        obj.pop();
        int param_4 = obj.top();
        int param_5 = obj.min();
        System.out.println(param_3 +" -- " +param_4 +" -- "+ param_5);
        /*
         * -3    -3
         * 0     -2
         * -2    -2
         * */

    }
}
class MinStack {
    //双栈实现最小栈
    Stack<Integer> stack;
    Stack<Integer> minStack;


    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.empty()) {
            minStack.push(x);
            return;
        }
        minStack.push(x > minStack.peek() ? minStack.peek() : x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }


    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}