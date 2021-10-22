import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class a59maxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {1, -1};
        int k = 3;
//        int k = 1;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }

    /*
     *   1   3   -1  -3  5   3   6   7
     *  [1   3   -1]
     * 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，
     * 这是 i 在 j 的左侧所保证的。因此，由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，
     * nums[i] 永久地移除。因此我们可以使用一个队列存储所有还没有被移除的下标。
     * 在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums 中对应的值是严格单调递减的。
     * */
    public static int[] maxSlidingWindow(int[] nums, int k) {

        int N = nums.length;
        if (N < k || N == 0) {
            return new int[]{};
        }
        int[] ans = new int[N - k + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int index = 0;
        ans[index] = nums[deque.peekFirst()];

        int l = 1;
        int r = k - 1;
        for (r = k; r < N; r++, l++) {
            while (!deque.isEmpty() && nums[r] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(r);
            if (l > deque.peekFirst()) {
                deque.pollFirst();
            }
            ans[++index] = nums[deque.peekFirst()];
        }

        return ans;
    }
}
