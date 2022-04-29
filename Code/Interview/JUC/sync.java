import java.util.concurrent.locks.ReentrantLock;
public class sync {
    public static void main(String[] args) {
        /*创建多线程的一般流程*/
        // 1. 创建资源类,在资源类中创建属性和方法 (为了实现高内聚,低耦合)
        Ticket ticket = new Ticket();
        // 2. 创建多线程,调用资源类方法

        /*3个售票员售出30张票*/
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "seller 1").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "seller2").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
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
