import java.util.ArrayList;
import java.util.List;

public class a728selfDividingNumbers {
    public static void main(String[] args) {
        System.out.println(selfDividingNumbers(1, 22));
    }

    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int val = i;
            boolean flag = true;
            while (val != 0) {
                int num = val % 10;
                val = val / 10;
                if (num == 0 ||i % num != 0) {
                    flag = false;
                    break;
                }

            }

            if (flag) list.add(i);
        }
        return list;
    }
}
