import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class a0303StackOfPlates {
    public static void main(String[] args) {


        StackOfPlates obj = new StackOfPlates(2);
        obj.push(1);
        obj.push(2);
        obj.push(3);
        int param_2 = obj.popAt(0);
        int param_3 = obj.popAt(0);
        int param_4 = obj.popAt(0);
        int param_5 = obj.pop();
        int param_6 = obj.pop();
    }

    static class StackOfPlates {

        ArrayList<Deque<Integer>> stack;
        Integer capacity;

        public StackOfPlates(int cap) {
            stack = new ArrayList<>();
            capacity = cap;
        }

        public void push(int val) {
            if (capacity == 0 || stack.isEmpty() || stack.get(stack.size() - 1).size() == capacity) {
                stack.add(new LinkedList<>());
            }
            stack.get(stack.size() - 1).push(val);
        }

        public int pop() {
            if (capacity == 0 || stack.isEmpty()) {
                return -1;
            }
            Deque<Integer> topStack = stack.get(stack.size() - 1);
            Integer ans = topStack.pop();
            if (topStack.isEmpty()) {
                stack.remove(stack.size() - 1);
            }
            return ans;
        }

        public int popAt(int index) {
            if (capacity == 0 || index < 0 || index >= stack.size()) {
                return -1;
            }
            Deque<Integer> topStack = stack.get(index);
            if (topStack.isEmpty()) {
                stack.remove(stack.size() - 1 - index);
                return -1;
            }
            Integer ans = topStack.pop();
            if (topStack.isEmpty()) {
                stack.remove(index);
            }
            return ans;
        }
    }

}
