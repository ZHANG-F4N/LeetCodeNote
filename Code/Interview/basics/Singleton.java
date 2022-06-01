import java.io.Serializable;

public class Singleton {
    //Singleton单例设计模式


    //
    /*
     * 1. 饿汉式
     * 唯一实例会提前创建,在类调用时就会创建
     * 可能会通过以下方法破坏单例
     * a. 通过反射,调用私有构造,可以在构造方法中预防
     * b. 实现了Serializable接口的类,通过反序列化破坏单例,实现readResolve()预防
     * c. unsafe破坏单例 无法预防
     * */
    // 实现序列化接口可以通过反序列化
    public static class Singleton1 implements Serializable {

        // 私有构造
        private Singleton1() {
            // 预防反射破坏单例
            if (INSTANCE != null) {
                throw new RuntimeException("单例对象不能重复创建");
            }
            System.out.println("private Singleton");
        }

        // 唯一实现
        private static final Singleton1 INSTANCE = new Singleton1();

        // 提供获取方法
        public static Singleton1 getInstance() {
            return INSTANCE;
        }

        // 防止反序列化破坏单例

        public Object readResolve() {
            return INSTANCE;
        }

        // ... 其他方法
    }

    /*
     * 2. 枚举饿汉式
     * a. 通过反射,调用私有构造,可以在构造方法中预防
     * b. 实现了Serializable接口的类,通过反序列化破坏单例,实现readResolve()预防
     * c. unsafe破坏单例 无法预防
     * */
    public enum Singleton2 {
        INSTANCE;

        Singleton2() {
            System.out.println("private Singleton2");
        }

        // 提供获取方法
        public static Singleton2 getInstance() {
            return INSTANCE;
        }
        // ... 其他方法
    }

    /*
     * 3. 懒汉式
     *
     * */
    static class Singleton3 {
        private static Singleton3 INSTANCE = null;

        private Singleton3() {
            System.out.println("private Singleton3");
        }

        // 加上synchronized防止多线程同时进入 INSTANCE = new Singleton3();
        public static synchronized Singleton3 getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new Singleton3();
            }
            return INSTANCE;
        }
        // ... 其他方法
    }

    /*
     * 4. DCL懒汉式
     * 方法三 synchronized 加载静态方法上,相当于加在Singleton4.class上,每次调用这个类都会加锁
     * 多线程下效率过低,创建单例后其实可以避免每次加锁
     * */
    static class Singleton4 {
        /*
         * 双检查锁要加 volatile
         * volatile解决共享变量的 可见性和有序性的问题
         * JVM在将代码编译为字节码后,在执行时,可能会因为优化而改变指令的执行顺序(指令重排序)
         * 在单线程情况可以正常执行,但是多线程就有可能出现问题
         * */
        private static volatile Singleton4 INSTANCE = null;

        private Singleton4() {
            System.out.println("private Singleton3");
        }

        // 双检查锁机制
        public static Singleton4 getInstance() {
            if (INSTANCE == null) {
                synchronized (Singleton4.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new Singleton4();
                    }
                }
            }
            return INSTANCE;
        }
        // ... 其他方法
    }

    /*
     * 5. 内部类懒汉式
     * 第一次调用getInstance才会触发内部类的加载链接初始化
     * */
    static class Singleton5 {
        private Singleton5() {
            System.out.println("private Singleton5");
        }

        private static class Holder {
            static Singleton5 INSTANCE = new Singleton5();
        }

        public static Singleton5 getInstance() {
            return Holder.INSTANCE;
        }
        // .. 其他方法
    }

}

/*
enum Sex {
        MALE, FEMALE;
    }

final class Sex extends Enum<Sex> {
    public static final Sex MALE;
    public static final Sex FEMALE;

    private Sex(String name, int ordinal) {
        super(name, ordinal);
    }

    static {
        MALE = new Sex("MALE", 0);
        FEMALE = new Sex("FEMALE", 0);
        $VALUES = $values();
    }

    private static final Sex[] $VALUES;

    private static Sex[] $values() {
        return new Sex[]{MALE, FEMALE};
    }

    public static Sex valueOf(String value) {
        return Enum.valueOf(Sex.class, value);
    }

}
*/
