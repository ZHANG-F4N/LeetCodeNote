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



    // 2. 枚举饿汉式
    public enum  Singleton2  {
    }

}
