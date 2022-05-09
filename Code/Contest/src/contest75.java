import java.util.HashSet;

public class contest75 {
    public static void main(String[] args) {
//        System.out.println(minBitFlips(3, 4));
//        System.out.println(triangularSum(new int[]{1, 2, 3, 4, 5}));
        System.out.println(numberOfWays("11100"));
        System.out.println(numberOfWays("001101"));
    }

    public static int minBitFlips(int start, int goal) {
        int val = start ^ goal;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((val & (1 << i)) != 0) ans++;
        }
        return ans;
    }

    public static int triangularSum(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] temp = new int[n];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                temp[j] = (nums[j] + nums[j + 1]) % 10;
            }
            System.arraycopy(temp, 0, nums, 0, n - i);
        }
        return temp[0];

    }

    public static long numberOfWays(String s) {

        HashSet<Integer> set = new HashSet<>();
        // 101 010
        char[] chars = s.toCharArray();
        int n = s.length();

        int[] zero = new int[n + 1];
        int[] one = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                one[i + 1] = one[i] + 1;
                zero[i + 1] = zero[i];
            } else {
                one[i + 1] = one[i];
                zero[i + 1] = zero[i] + 1;
            }
        }

        long ans = 0L;

        for (int i = 1; i < n - 1; i++) {
            if (chars[i] == '1') {
                if (zero[i + 1] == 0 || zero[n] - zero[i] == 0) continue;
                ans += zero[i + 1] * (zero[n] - zero[i]);
            } else {
                if (one[i + 1] == 0 || one[n] - one[i] == 0) continue;
                ans += one[i + 1] * (one[n] - one[i]);
            }
        }

        return ans;
    }
}
