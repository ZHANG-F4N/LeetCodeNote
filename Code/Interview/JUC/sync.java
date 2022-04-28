<<<<<<< HEAD
public class sync {
    public static void main(String[] args) {
        /*创建多线程的一般流程*/
        // 1. 创建资源类,在资源类中创建属性和方法 (为了实现高内聚,低耦合)
        Ticket ticket = new Ticket();
        // 2. 创建多线程,调用资源类方法

=======
import java.util.concurrent.locks.ReentrantLock;

public class sync {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        /*3个售票员售出30张票*/
>>>>>>> 5144292434e5e256b83042bf87cf054a464e6e1d
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
<<<<<<< HEAD
        }, "seller 1").start();
=======
        }, "seller1").start();
>>>>>>> 5144292434e5e256b83042bf87cf054a464e6e1d
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
<<<<<<< HEAD
        }, "seller 2").start();
=======
        }, "seller2").start();
>>>>>>> 5144292434e5e256b83042bf87cf054a464e6e1d
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
<<<<<<< HEAD
        }, "seller 3").start();


    }

    /*一个多线程例子*/
    /*3个售票员 售出30张票*/
    static class Ticket {
        private int number = 30;

        private synchronized void sale() {
            System.out.println(Thread.currentThread().getName());
            if (number <= 0) {
                System.out.println("Ticket out...");
                return;
            }
            System.out.println("sale 1 ticket,remain " + number--);
        }

    }
}


=======
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
>>>>>>> 5144292434e5e256b83042bf87cf054a464e6e1d
