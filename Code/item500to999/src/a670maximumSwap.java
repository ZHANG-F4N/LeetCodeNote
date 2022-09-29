import java.util.ArrayList;

public class a670maximumSwap {
    public static void main(String[] args) {
        System.out.println(maximumSwap(98368));
        System.out.println(maximumSwap(2736));
    }

    public static int maximumSwap(int num) {

        ArrayList<Integer> list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        int n = list.size();
        int[] val = new int[n];
        for (int i = n - 1; i >= 0; i--) val[n - i - 1] = list.get(i);

        for (int i = 0; i < n; i++) {
            int m = i;
            for (int j = n - 1; j > i; j--) {
                if (val[m] < val[j]) m = j;
            }
            if (m != i) {
                int t = val[i];
                val[i] = val[m];
                val[m] = t;
                break;
            }

        }
        int ans = 0, t = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans += val[i] * t;
            t *= 10;

        }
        return ans;
    }
}
