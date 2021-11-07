import java.util.Arrays;
import java.util.HashSet;

public class contest266a3 {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(7, new int[]{15, 10, 10}));
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
    }
    /*
     * 11+6  / 2 == 2
     * 2 * 6 = 12
     * 17 - 12 = 5
     * 11-8=3
     * 6-4=2
     *
     * 2 2 2 2 2 2
     *
     * 17 / 6 +1 = 3
     * 3  3  3  3  3  3
     *
     * 12+6
     * 16 1
     *  3   3   3   3   3   3
     *  3   6   9   12  15  18
     * */

    public static int minimizedMaximum(int n, int[] quantities) {
        int ans = 0;
        int sort = quantities.length;
        int sum = Arrays.stream(quantities).sum();
        int num = (sum / n) + 1;
        int[] dp = new int[sort];
        quantities
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] < num) {
               dp[i] = 1;
            }
        }
        return ans;
    }
}
