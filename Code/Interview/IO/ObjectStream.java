import java.io.*;

public class ObjectStream {


    /*
     *  ObjectInputStream ObjectOutputStream
     *  用以存储和读取 对象类型 和 基本类型 的处理流
     *  通过 序列化 与 反序列化 来进行对对象数据的 保存 和 读取
     *  ObjectOutputStream 和 ObjectInputStream 不能序列化 static和transient 修饰的成员变量
     * */
    /*
     *  对象序列化 : 将内存中的Java对象转换为平台无关的二进制流 这样就可以将对象进行与平台无关的 传输和保存
     *  实现了Serializable接口的对象 可以转化为 字节数据
     *
     * */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Object 类型的写入
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Interview//IO//Object.dat"));
        oos.writeObject(new Person("我爱北京",23));
        oos.flush();
        oos.close();

        // Object 读入
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Interview//IO//Object" +
                ".dat"));

        Person str = (Person) ois.readObject();
        System.out.println(str);
        ois.close();

    }

    static class Person implements Serializable {
        // 序列版本号
        private static final long serialVersionUID = 46516164984L;
        private String name;
        private int age;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
