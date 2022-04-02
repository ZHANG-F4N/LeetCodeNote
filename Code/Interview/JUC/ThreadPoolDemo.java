import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {

//        // 一池多线程
//        ExecutorService threadPool1 = Executors.newFixedThreadPool(3);
//        try {
//            /*模拟5个线程放入大小为3的线程池*/
//            for (int i = 0; i < 10; i++) {
//                threadPool1.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭线程池
//            threadPool1.shutdown();
//        }
//
//
//        // 一池一线程
//        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
//        try {
//            /*模拟5个线程放入大小为1的线程池*/
//            for (int i = 0; i < 5; i++) {
//                threadPool2.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭线程池
//            threadPool2.shutdown();
//        }
//
//        // 线程池可扩容
//        ExecutorService threadPool3 = Executors.newCachedThreadPool();
//        try {
//            /*模拟5个线程放入大小为1的线程池*/
//            for (int i = 0; i < 5; i++) {
//                threadPool3.execute(() -> {
//                    System.out.println(Thread.currentThread().getName() + " 办理业务");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭线程池
//            threadPool3.shutdown();
//        }


        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 自定义线程池使用
        try {
            /*模拟5个线程放入大小为5的线程池*/
            for (int i = 0; i < 5; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }


    }
}
