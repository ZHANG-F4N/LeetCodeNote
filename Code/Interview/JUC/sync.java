import java.util.concurrent.locks.ReentrantLock;

public class sync {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        /*3个售票员售出30张票*/
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "seller1").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "seller2").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "seller3").start();
    }

    static class Ticket {
        public int number = 30;
        private final ReentrantLock lock = new ReentrantLock(true);
//        private final ReentrantLock lock = new ReentrantLock(true);

        public void sale() {
            // 加锁
            lock.lock();
            try {
                if (number > 0)
                    System.out.println(Thread.currentThread().getName() + "买了1张票" + ",剩余" + (--number) + "张票");
                else System.out.println(Thread.currentThread().getName() + "没买到");
            } finally {
                // 解锁 为了避免解锁前出现异常而导致没有解锁,需放入finally中
                lock.unlock();
            }
        }
    }

    static class AddMinus {

    }
}
