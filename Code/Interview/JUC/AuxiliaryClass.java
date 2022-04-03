import java.util.Random;
import java.util.concurrent.*;

public class AuxiliaryClass {
    public static void main(String[] args) throws InterruptedException {
//        /*CountDownLatch实现5个同学陆续离开教室,全部离开后锁门*/
//        /*设置计数器*/
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "离开");
//                // 计数器 -1
//                countDownLatch.countDown();
//            }, "No." + i).start();
//        }
//        //等待
//        countDownLatch.await();
//        System.out.println(Thread.currentThread().getName() + "锁门");
//
//        /* CyclicBarrier集齐7颗龙珠召唤神龙 */
//        final int NUMBER = 7;
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
//            System.out.println("---集齐7颗龙珠召唤神龙---");
//        });
//
//        // 集齐龙珠的过程
//        for (int i = 0; i < 15; i++) {
//            new Thread(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + "星龙珠已收集.");
//                    // 等待
//                    cyclicBarrier.await();
//                    // cyclicBarrier.await();之后的代码必须等达到CyclicBarrier的屏障才会执行
//                    System.out.println("WDNMD");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//
//            }, String.valueOf(i + 1)).start();
//        }


        /*Semaphore实现6辆汽车停到3个停车位*/
        // 构造函数传参为许可数量
        Semaphore semaphore = new Semaphore(3);


        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 获取许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到了车位");
                    // 随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "--离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, i + "号车").start();
        }


    }


}
