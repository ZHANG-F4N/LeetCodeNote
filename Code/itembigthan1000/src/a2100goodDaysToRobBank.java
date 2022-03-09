import java.util.ArrayList;
import java.util.List;

public class a2100goodDaysToRobBank {
    public static void main(String[] args) {
        System.out.println(goodDaysToRobBank(new int[]{5, 3, 3, 3, 5, 6, 2}, 2));
    }

    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] delta = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] > security[i - 1]) delta[i] = 1;
            else if (security[i] == security[i - 1]) delta[i] = 0;
            else delta[i] = -1;
        }
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (delta[i - 1] == 1){
                a[i] = a[i - 1] + 1;
                b[i] = b[i - 1];
            }
            else if (delta[i - 1] == -1) {
                b[i] = b[i - 1] + 1;
                a[i] = a[i - 1];
            }
            else {
                a[i] = a[i - 1];
                b[i] = b[i - 1];
            }
        }
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = time; i < n - time; i++) {
            if (a[i+1] - a[i - time+1] == 0 && b[i + time+1] - b[i+1] == 0) list.add(i);
        }


        return list;
    }
}
