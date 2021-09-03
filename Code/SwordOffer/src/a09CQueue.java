import java.util.Stack;

public class a09CQueue {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        obj.appendTail(1);
        obj.appendTail(2);
        obj.appendTail(3);

        int param_2 = obj.deleteHead();
        System.out.println(param_2);
        obj.appendTail(4);
        obj.appendTail(5);
        param_2 = obj.deleteHead();
        System.out.println(param_2);

    }
}


/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
class CQueue {
    private Stack<Integer> stackA;
    private Stack<Integer> stackB;

    public CQueue() {
        stackA = new Stack<Integer>();
        stackB = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stackA.push(value);
    }

    public int deleteHead() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return -1;
            } else {
                while (!stackA.empty()) {
                    stackB.push(stackA.pop());
                }
            }
        }
        return stackB.pop();
    }
}
