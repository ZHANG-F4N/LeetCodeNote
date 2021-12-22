import jdk.swing.interop.SwingInterOpUtils;

public class a453minMoves {
    public static void main(String[] args) {
        System.out.println(minMoves(new int[]{1, 2, 3, 4, 5, 5}));
    }

    public static int minMoves(int[] nums) {
        // 给n-1个数加1, 就是给1个数减1
        int N = nums.length;
        int min = nums[0];
        for (int i = 0; i < N; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += nums[i] - min;
        }
        return ans;

    }
}
