import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class a14smallestK {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        //System.out.println(Arrays.toString(smallestK2(arr, k)));
        System.out.println(firstUniqChar("loveleetcode"));
    }

    //快排实现
//    能用快排的原因，题目里面有【以任意顺序】 步骤
//
//    随便找个数，进行一轮快排
//    快排结束，进行数据划分,假设这个数属于第t个
//    t==k，那么返回前t个；
//    t > k，那么问题规模缩小为在前面t-1个数找k个；
//    t < k，说明已经确定前t个数属于前k个，但是第t+1到k这些数还没确定，
//    那么问题规模缩小为从t+1到右边界找k-t个数
    public static int[] smallestK2(int[] arr, int k) {
        qsort(arr, 0, arr.length, k);
        System.out.println(Arrays.toString(smallestK(arr, k)));
        return Arrays.copyOfRange(arr, 0, k);

    }

    public static void qsort(int[] arr, int left, int right, int k) {
        int l = left;
        int r = right;
        int point = (left + right) / 2;
        while (left < right) {
            while (left < right && arr[left] < arr[point]) left++;
            while (left < right && arr[right] > arr[point]) right--;
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        if (left == k) {
            return;
        }
        if (left > k) {
            qsort(arr, l, left, k);
        }
        if (left < k) {
            qsort(arr, left, right, k - left);
        }
    }

    // 堆实现
    public static int[] smallestK(int[] arr, int k) {
        int[] ans = new int[k];
        if (k == 0) {
            return ans;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (priorityQueue.peek() > arr[i]) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i] = priorityQueue.poll();
        }
        return ans;
    }

    public static char firstUniqChar(String s) {

        HashMap<Character, Integer> num = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            num.put(s.charAt(i), num.getOrDefault(s.charAt(i), 0)+1);
        }
        for (int i = 0; i < n; i++) {
            if (num.get(s.charAt(i)) == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }
}
