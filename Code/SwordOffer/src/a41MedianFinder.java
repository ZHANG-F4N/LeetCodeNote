import java.util.Comparator;
import java.util.PriorityQueue;

public class a41MedianFinder {
    static class MedianFinder {

        /**
         * initialize your data structure here.
         */
        private PriorityQueue<Integer> smallHeap;
        private PriorityQueue<Integer> bigHeap;

        public MedianFinder() {
            // small Heap has N/2 - 1 number
            // big Heap has N/2 + 1 number
            smallHeap = new PriorityQueue<>();
            bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
        }

        public void addNum(int num) {

            if (bigHeap.isEmpty() || num <= bigHeap.peek()) {
                bigHeap.offer(num);
            } else {
                smallHeap.offer(num);
            }
            int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 - N2 > 1) {
                smallHeap.offer(bigHeap.poll());
            }
            if (N1 - N2 <= -1) {
                bigHeap.offer(smallHeap.poll());
            }
        }

        public double findMedian() {
            int N1 = bigHeap.size();
            int N2 = smallHeap.size();
            if (N1 == N2) {
                return ((double) smallHeap.peek() + bigHeap.peek()) / 2;
            }
            return bigHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(4);
        obj.addNum(5);
        obj.addNum(1);
        obj.addNum(6);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(7);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        obj.addNum(8);
        System.out.println(obj.findMedian());

    }
}


