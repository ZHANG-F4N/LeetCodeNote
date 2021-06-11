import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class a215findKthLargest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    //自己实现大顶堆MaxHeap
    public static int findKthLargest(int[] nums, int k) {

        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        //移除前K-1大的数
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            heapSize--;
            swap(nums, 0, i);
            adjustMaxHeap(nums, 0, heapSize);
        }

        return nums[0];
    }

    public static void buildMaxHeap(int[] a, int heapSize) {
        //从第一个拥有子节点的位置开始调整。
        for (int i = (heapSize >> 1); i >= 0; --i) {
            adjustMaxHeap(a, i, heapSize);
        }

    }

    //调整大顶堆
    // i--局部堆头结点
    // heapSize--堆尾结点
    public static void adjustMaxHeap(int[] a, int i, int heapSize) {
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;
        int bigNode = i;
        if (leftChild < heapSize && a[leftChild] > a[bigNode]) {
            bigNode = leftChild;
        }
        if (rightChild < heapSize && a[rightChild] > a[bigNode]) {
            bigNode = rightChild;
        }
        if (bigNode != i) {
            swap(a, bigNode, i);
            adjustMaxHeap(a, bigNode, heapSize);
        }
    }


    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /*
    //堆排序 API
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            if (heap.size() == k) {
                if (heap.peek() <= nums[i]) {
                    heap.poll();
                    heap.offer(nums[i]);
                }
            } else {
                heap.offer(nums[i]);
            }
        }
        return heap.peek();
    }
    */
    /*
    //Sort API
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    */

}
