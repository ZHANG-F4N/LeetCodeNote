import java.util.*;

public class a347topKFrequent {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        topKFrequent(nums, k);

    }

    public static int[] topKFrequent(int[] nums, int k) {
//        int[][] frequent = new int[][]
//        Arrays.sort(nums);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
           /*
           Parameters:
            o1 - the first object to be compared.
            o2 - the second object to be compared.
           Returns:
            a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
            从小到大排序 o1 - o2
            从大到小排序 o2 - o1
            */
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
//
        });

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int item = entry.getKey();
            int freq = entry.getValue();
            if (priorityQueue.size() == k){
                if (priorityQueue.peek()[1] < freq){
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{item,freq});
                }
            }else {
                priorityQueue.offer(new int[]{item,freq});
            }
        }

        int[] ans=new int[k];
        for (int i = k-1; i >=0; i--) {
            ans[i] = priorityQueue.poll()[0];
        }
        //System.out.println(Arrays.toString(ans));
        return ans;
    }
}
