import java.util.Arrays;
import java.util.Random;

public class a71weightRandom {
    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{1});
        int param_1 = obj.pickIndex();
        int param_2 = obj.pickIndex();
        int param_3 = obj.pickIndex();
    }

    static class Solution {
        int[] preSum;
        int total;
        //  w   3   1   4   2
        // pre  3   4   (8   10] 代表具体随机的值。
        //  i   [1   2   3]   [4]   [5   6   7   8]   [9   10]

        public Solution(int[] w) {
            preSum = new int[w.length];
            total = Arrays.stream(w).sum();
            for (int i = 0; i < w.length; i++) {
                preSum[i] = i == 0 ? w[0] : w[i] + preSum[i - 1];
            }

        }

        public int pickIndex() {
            int seed = (int) (Math.random() * total) + 1;
            int idx = Arrays.binarySearch(preSum, seed);
            if (idx < 0) {
                idx = -idx - 1;
            }
            return idx;
        }
    }
}
