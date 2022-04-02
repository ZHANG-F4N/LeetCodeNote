import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<String>(3);

        // 第一组
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

        System.out.println(queue.element());
        System.out.println(queue.remove());
        // 队列满,报异常 java.lang.IllegalStateException: Queue full
        System.out.println(queue.add("d"));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        // 队列空报异常java.util.NoSuchElementException
        System.out.println(queue.remove());

        // 第一组
        queue.add("a");  // 向队列中加元素
        queue.offer("c", 100, TimeUnit.SECONDS); // 设置超时失效
        queue.element(); // 返回队列头元素
        queue.remove();  // 移除队列头
        // 第二组
        queue.offer("a");
        queue.poll();
        queue.peek();
        // 第三组
        queue.put("a");  // 超过容量阻塞
        queue.take();       // 空了会阻塞


    }
}
