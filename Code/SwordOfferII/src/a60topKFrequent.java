import java.util.*;

public class a60topKFrequent {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int idx = 0;
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (idx < k) {
                queue.offer(new Integer[]{en.getKey(), en.getValue()});
            } else {
                if (en.getValue() >= queue.peek()[1]) {
                    queue.poll();
                    queue.offer(new Integer[]{en.getKey(), en.getValue()});
                }
            }
            idx++;
        }
        int[] ans = new int[k];
        idx = 0;
        Iterator<Integer[]> it = queue.iterator();
        while (it.hasNext()) {
            ans[idx++] = it.next()[0];
        }
        return ans;
    }

}
