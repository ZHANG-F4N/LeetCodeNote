import java.util.ArrayList;
import java.util.List;

public class a386lexicalOrder {
    public static void main(String[] args) {
        System.out.println(lexicalOrder(123));
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        int number = 1;
        for (int i = 0; i < n; i++) {
            list.add(number);
            if (number * 10 <= n) {
                number = number * 10;
            } else {
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return list;
    }

}
