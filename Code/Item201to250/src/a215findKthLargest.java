import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class a215findKthLargest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }

    //自己实现大顶堆MaxHeap
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

    public static void buildMaxHeap(int[] a, int heapSize){
        //从第一个拥有子节点的位置开始调整。

    }

    //调整大顶堆
    // i--局部堆头结点
    // heapSize--堆尾结点
    public static void adjustMaxHeap(int[] a, int i, int heapSize) {
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;
        int bigNode = i;
        if (leftChild < heapSize && a[leftChild] > a[i]) {
            bigNode = leftChild;
        }
        if (rightChild < heapSize && a[rightChild] > a[i]) {
            bigNode = leftChild;
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
