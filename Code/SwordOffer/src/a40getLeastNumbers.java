import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class a40getLeastNumbers {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 1};
//        int[] nums = {4, 5, 1, 6, 2, 7, 3, 8};

        System.out.println(Arrays.toString(getLeastNumbers(nums, 1)));
    }

    public static int[] getLeastNumbers(int[] arr, int k) {


        if (k == 0) {
            return new int[0];
        }
        int[] ans = new int[k];

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }

//    //快排
//    public static int[] getLeastNumbers2(int[] arr, int k) {
//
//        return Arrays.copyOfRange(arr, 0, k);
//    }
//
//    // 基于随机的划分
//    private int randomizedPartition(int[] nums, int l, int r) {
//        int i = new Random().nextInt(r - l + 1) + l;
//        swap(nums, r, i);
//        return partition(nums, l, r);
//    }
//
//    private int partition(int[] nums, int l, int r) {
//        int pivot = nums[r];
//        int i = l - 1;
//        for (int j = l; j <= r - 1; ++j) {
//            if (nums[j] <= pivot) {
//                i = i + 1;
//                swap(nums, i, j);
//            }
//        }
//        swap(nums, i + 1, r);
//        return i + 1;
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
}
