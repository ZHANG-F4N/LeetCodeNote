import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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


    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        // 1. 优先队列存放的是二元组(num,index) : 大顶堆（元素大小不同按元素大小排列，元素大小相同按下标进行排列）
        // num :   是为了比较元素大小
        // index : 是为了判断窗口的大小是否超出范围
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o2[0] - o1[0];
            }
        });

        // 2. 优选队列初始化 : k个元素的堆
        for (int i = 0; i < k; i++) queue.add(new int[]{nums[i], i});
        ans[0] = queue.peek()[0];
        for (int i = k; i < n; i++) {
            queue.offer(new int[]{nums[i], i});
            while (queue.peek()[1] <= i - k) queue.poll(); // 将下标不在滑动窗口中的元素都干掉
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }


}
