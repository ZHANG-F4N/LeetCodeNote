import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockProblem {
    static class Phone {
        public static synchronized void sendA() throws Exception {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("---sendA---");
        }

        public synchronized void sendB() throws Exception {
            System.out.println("---sendB---");
        }

        public void sayHello() {
            System.out.println("---Hello---");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendA();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "A").start();

        new Thread(() -> {
            try {
                phone2.sendB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();


        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }
                }
            }
        }, "t1").start();

        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                } finally {
//                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }

}
