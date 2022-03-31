import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockCommunication {
    public static void main(String[] args) {
//        Share share = new Share();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.add();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "AA").start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.minus();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "BB").start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.add();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "CC").start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                try {
//                    share.minus();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "DD").start();
        Custom custom = new Custom();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                custom.print3(i);
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                custom.print6(i);
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                custom.print9(i);
            }
        }, "CC").start();
    }

    /*lock实现一个由两个线程操作同一个变量,进行01切换的类*/
    static class Share {
        private int number = 0;
        // 新建锁对象
        private ReentrantLock lock = new ReentrantLock();
        //
        private Condition condition = lock.newCondition();

        // +1
        public void add() throws InterruptedException {
            lock.lock();
            try {
                while (number != 0) {
                    condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName() + "::" + number);
                // 通知全部
                condition.signalAll();

            } finally {
                lock.unlock();
            }
        }

        //-1
        public void minus() throws InterruptedException {

            lock.lock();
            try {
                while (number != 1) {
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName() + "::" + number);
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }

    /* lock 实现一个三个线程按顺序轮换执行即A->B->C->A->B->C....*/
    static class Custom {
        // 1-->A 2-->B 3-->C
        private int flag = 1;
        private ReentrantLock lock = new ReentrantLock();
        // 创建三个 condition
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        // 打印3轮 参数为第几轮
        public void print3(int loop) {

            // 上锁
            lock.lock();
            try {
                while (flag != 1) c1.await();
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数 :" + loop);
                }
                // 修改标志位
                flag = 2;
                // 通知c2
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }

        public void print6(int loop) {
            // 上锁
            lock.lock();
            try {
                while (flag != 2) c2.await();
                for (int i = 0; i < 6; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数 :" + loop);
                }
                // 修改标志位
                flag = 3;
                // 通知c3
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }

        public void print9(int loop) {
            // 上锁
            lock.lock();
            try {
                while (flag != 3) c3.await();
                for (int i = 0; i < 9; i++) {
                    System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数 :" + loop);
                }
                // 修改标志位
                flag = 1;
                // 通知c1
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

}
