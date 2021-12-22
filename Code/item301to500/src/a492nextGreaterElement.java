import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class a492nextGreaterElement {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int N = nums2.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] index = new int[N];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            hashMap.put(nums2[i], i);
            if (!deque.isEmpty() && deque.peek() <= nums2[i]) {
                while (!deque.isEmpty() && deque.peek() < nums2[i]) {
                    index[hashMap.get(deque.peek())] = nums2[i];
                    deque.pop();
                }
            }
            deque.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = index[hashMap.get(nums1[i])] == 0 ? -1 : index[hashMap.get(nums1[i])];
        }
        return ans;
    }
}
