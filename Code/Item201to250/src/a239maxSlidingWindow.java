import java.util.ArrayDeque;
import java.util.Arrays;

public class a239maxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow2(nums, k)));
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        ans[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];

        }

        return ans;

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = k - 1;

        int maxVal = nums[0];
        int maxIdx = 0;

        for (int i = 0; i <= right; i++) {
            if (nums[i] > maxVal) {
                maxIdx = i;
                maxVal = nums[i];
            }
        }
        int[] ans = new int[nums.length - k + 1];

        ans[0] = nums[maxIdx];
        right++;
        left++;
        for (; right < nums.length; right++, left++) {
            if (maxIdx < left) {
                maxVal = nums[left];
                for (int i = left; i <= right; i++) {
                    if (nums[i] > maxVal) {
                        maxIdx = i;
                        maxVal = nums[i];
                    }
                }
            } else {
                if (nums[right] > maxVal) {
                    maxVal = nums[right];
                    maxIdx = right;
                }
            }

            ans[left] = maxVal;
        }
        return ans;
    }

}
