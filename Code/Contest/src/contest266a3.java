import java.util.Arrays;
import java.util.HashSet;

public class contest266a3 {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(1, new int[]{1}));
        System.out.println(minimizedMaximum(7, new int[]{15, 10, 10}));
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
    }

    /*
     * 二分搜索
     * 假设每个店最大商品数是 x
     * 那么大于x的数也都可以满足条件。
     *
     * */
    public static int minimizedMaximum(int n, int[] quantities) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < quantities.length; i++) {
            right = right > quantities[i] ? right : quantities[i];
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (judge(n, quantities, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 最多分配 x 满足条件返回 true 不满足条件返回 false
    public static boolean judge(int n, int[] quantities, int x) {
        if (x == 0) {
            return Arrays.stream(quantities).sum() == 0;
        }
        int need = 0;
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] <= x) {
                need++;
            } else {
                need += ((quantities[i] - 1) / x) + 1;
            }
            if (need > n) {
                return false;
            }
        }
        return need <= n;
    }


}
