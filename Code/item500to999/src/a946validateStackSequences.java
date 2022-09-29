import java.util.ArrayDeque;
import java.util.HashSet;

public class a946validateStackSequences {
    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int idx = 0;
        for (int num : pushed) {
            deque.addLast(num);
            while (!deque.isEmpty() && deque.peekLast() == popped[idx]) {
                idx++;
                deque.pollLast();
            }
        }
        return deque.isEmpty();
    }
}
