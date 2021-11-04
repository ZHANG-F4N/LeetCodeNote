import java.util.*;

public class contest265a3 {
    public static void main(String[] args) {
//        System.out.println(minimumOperations(new int[]{3, 5, 7}, 0, -4));
        System.out.println(minimumOperations(new int[]{1, 3}, 6, 4));
    }


    public static int minimumOperations(int[] nums, int start, int goal) {
        int op = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        TreeSet<Integer> set = new TreeSet<>();
        deque.offer(start);
        int levelNum = 1;
        while (!deque.isEmpty()) {
            start = deque.poll();
            levelNum--;
            if (start == goal) {
                return op;
            }
            if (start < 0 || start > 1000) {
                continue;
            }
            for (int i = 0; i < nums.length; i++) {
                if (start < goal && !deque.contains(start + nums[i])) {
                    deque.offer(start + nums[i]);
                }
                if (start > goal && !deque.contains(start - nums[i])) {
                    deque.offer(start - nums[i]);
                }
                if (!deque.contains(start ^ nums[i]))
                    deque.offer(start ^ nums[i]);
            }
            op++;
        }


        return op;
    }


}
