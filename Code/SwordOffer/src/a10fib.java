import java.util.ArrayList;
import java.util.List;

public class a10fib {

    public static void main(String[] args) throws CloneNotSupportedException {
//        List<Integer> list = new ArrayList<>();
//        int result = list.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(6);
//        list.add(100);
//        result = list.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
//        new classlo();
//        System.out.println(result);
        Address addr = new Address();

        addr.setAdd("beijing");
        Student stu1 = new Student();
        stu1.setAddr(addr);
        Student stu2 = (Student) stu1.clone();
        System.out.println("xueshen 1" + stu1.getAddr().getAdd());
        System.out.println("xueshen 2" + stu2.getAddr().getAdd());

        addr.setAdd("tianjin");
        System.out.println("xueshen 1" + stu1.getAddr().getAdd());
        System.out.println("xueshen 2" + stu2.getAddr().getAdd());
        System.out.println(fib(7));
    }

    public static int fib(int n) {

        if (n == 1 || n == 0) {
            return 1;
        }
        int fn1 = 1;
        int fn2 = 1;
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (fn1 + fn2) % 1000000007;
            fn1 = fn2;
            fn2 = ans;
        }

        return ans % 1000000007;
    }
}

class classlo {
    public classlo() {
        System.out.println("构造");
    }

    {
        System.out.println("代码块");
    }

    static {
        System.out.println("static");
    }
}

class Student implements Cloneable {
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private Address addr;

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    public Address getAddr() {
        return addr;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Student stu = null;
        try {
            stu = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            ;
        }
        return stu;
    }
}

class Address {
    private String add;

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAdd() {
        return add;
    }
}
