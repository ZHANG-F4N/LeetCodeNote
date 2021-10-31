import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class a59KthLargest {
    public static void main(String[] args) {
        KthLargest obj = new KthLargest(3, new int[]{4, 5, 8, 2});
        int param_1 = obj.add(3);
        int param_2 = obj.add(5);
        int param_3 = obj.add(10);
        int param_4 = obj.add(9);
        int param_5 = obj.add(4);

        //  2   4   5   8
        //  2   3   4   5   8
        //  2   3   4   5   5   8
        //  2   3   4   5   5   8   10
    }
}

class KthLargest {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        int min = k < nums.length ? k : nums.length;
        for (int i = 0; i < min; i++) {
            queue.offer(nums[i]);
        }

        for (int i = min; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}
