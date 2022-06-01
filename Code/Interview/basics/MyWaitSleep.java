public class MyWaitSleep {

    static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
//        illegalWait();
        waiting();
    }

    private static void illegalWait() throws InterruptedException {
//        LOCK.wait(); 报错 须配合 对象锁
        synchronized (LOCK) { // wait 锁必须配合 对象锁 使用,不然会报错
            LOCK.wait();
        }
    }

    private static void waiting() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK){
                try {
                    System.out.println("t1 -- > waiting");
                    Thread.sleep(5000L);// 不会释放LOCK锁
                    LOCK.wait(5000L); // 会释放LOCK锁
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
//        t1.interrupt();// 可以用来强制唤醒
        Thread.sleep(100);
        synchronized (LOCK){// 如果t1 内使用 sleep,那么就得等待
            System.out.println("main");
        }
    }
}
