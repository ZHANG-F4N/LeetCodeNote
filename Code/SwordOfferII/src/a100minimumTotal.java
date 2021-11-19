import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a100minimumTotal {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list = Arrays.asList(new Integer[]{2});
        triangle.add(list);
        list = Arrays.asList(new Integer[]{3, 4});
        triangle.add(list);
        list = Arrays.asList(new Integer[]{6, 5, 7});
        triangle.add(list);
        list = Arrays.asList(new Integer[]{4, 1, 8, 3});
        triangle.add(list);

        System.out.println(minimumTotal(triangle));
    }
    /*
     *   2
     *   3   4
     *   6   5   7
     *   4   1   8   3
     *   2
     *
     *
     * */

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        int[] preDp = new int[n];
        int[] dp = new int[n];
        preDp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[0] = preDp[0] + triangle.get(i).get(0);
            dp[i] = preDp[i - 1] + triangle.get(i).get(i);
            for (int j = 1; j < i; j++) {
                dp[j] = Math.min(preDp[j], preDp[j - 1]) + triangle.get(i).get(j);
            }
            preDp = Arrays.copyOf(dp, i+1);
        }
        int min = Integer.MAX_VALUE;
        for (int i : dp) {
            if (i < min) {
                min = i;
            }
        }
        return min;


    }
}
