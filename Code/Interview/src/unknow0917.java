import java.util.Arrays;

public class unknow0917 {
    public static void main(String[] args) {
        String s1 = "a"; // 放入常量池中的串池 StringTable
        String s2 = "b"; // 放入常量池中的串池 StringTable
        String s3 = "ab";// 放入常量池中的串池 StringTable

        String s4 = s1 + s2; //新建了对象 等价于下面这句话
        // new StringBuilder().append("a").append("b").toString()
        // new String("ab")

        String s5 = "a" + "b"; // 编译期优化 ,发现存在于常量池
        System.out.println(s3 == s4); // false
        System.out.println(s3 == s5); // true
    }
}
