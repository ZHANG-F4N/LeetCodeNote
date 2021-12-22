import java.util.Arrays;
import java.util.Random;

public class a384shuffleArray {
    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{1, 2, 3});
        int[] param_1 = obj.reset();
        int[] param_2 = obj.shuffle();
    }

    static class Solution {
        int[] array;

        public Solution(int[] nums) {
            array = Arrays.copyOf(nums, nums.length);
        }

        public int[] reset() {
            int[] ans = Arrays.copyOf(array, array.length);
            return ans;
        }

        public int[] shuffle() {
            int n = array.length;
            Random random = new Random();
            int[] ans = Arrays.copyOf(array, n);
            for (int i = 0; i < n; i++) {
                //greater than or equal to {0.0} and less than {1.0}.
                // 不会等于1,所以会少最后一个位置.
                //int idx = (int) (Math.random() * (n - i - 1));

                int idx =random.nextInt(n - i);
                System.out.println(idx);
                int temp = ans[n - i - 1];
                ans[n - i - 1] = ans[idx];
                ans[idx] = temp;
            }
            return ans;
        }
    }
}
