import java.io.ObjectInputStream;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLock {
    public static void main(String[] args) {


        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        // 锁降级



        // 获取读锁
        readLock.lock();
        System.out.println("---Read---");
        // 获取写锁
        writeLock.lock();
        System.out.println("---Write---");



        readLock.unlock();
        writeLock.unlock();






        MyCache myCache = new MyCache();
        //  创建线程放数据
        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }

        //  创建线程取数据
        for (int i = 0; i < 3; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }

    static class MyCache {
        // 创建Map集合
        private Map<String, Object> map = new HashMap<>();

        private ReadWriteLock rwLock = new ReentrantReadWriteLock();

        // 放数据
        public void put(String key, Object val) {
            //添加写锁
            rwLock.writeLock().lock();

            try {
                System.out.println(Thread.currentThread().getName() + " 正在进行写操作 " + key);
                TimeUnit.MICROSECONDS.sleep(300);
                map.put(key, val);
                System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                rwLock.writeLock().unlock();
            }
        }

        // 取数据
        public Object get(String key) {
            // 添加读锁
            rwLock.readLock().lock();

            Object res = null;
            try {
                System.out.println(Thread.currentThread().getName() + " 正在进行读操作 " + key);
                TimeUnit.MICROSECONDS.sleep(300);
                res = map.get(key);
                System.out.println(Thread.currentThread().getName() + " 读完了 " + key);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放读锁
                rwLock.readLock().unlock();
            }

            return res;
        }


    }
}
