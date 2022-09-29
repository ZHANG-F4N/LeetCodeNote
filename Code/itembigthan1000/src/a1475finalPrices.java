import java.util.ArrayDeque;
import java.util.Arrays;

public class a1475finalPrices {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(finalPrices(new int[]{8, 4, 6, 2, 3})));
    }

    public static int[] finalPrices(int[] prices) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && prices[i] < deque.getLast()) deque.pollLast();
            ans[i] = deque.isEmpty() ? prices[i] : prices[i] - deque.getLast();
            deque.addLast(prices[i]);
        }
        return ans;
    }
}
