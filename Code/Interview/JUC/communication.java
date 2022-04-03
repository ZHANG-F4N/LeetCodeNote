public class communication {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.minus();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();

    }

    /*实现一个由两个线程操作同一个变量,进行01切换的类*/
    static class Share {

        private int number = 0;

        public synchronized void add() throws InterruptedException {
            // 不是0时进行等待
            while (number != 0) this.wait();
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知其他线程
            this.notifyAll();
        }

        public synchronized void minus() throws InterruptedException {
            while (number != 1) this.wait();
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            // 通知其他线程
            this.notifyAll();
        }
    }




}
