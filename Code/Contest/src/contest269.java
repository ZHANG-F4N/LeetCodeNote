import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class contest269 {
    public static void main(String[] args) {
        System.out.println(getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3));
        System.out.println(minimumDeletions(new int[]{-1, -53, 93, -42, 37, 94, 97, 82, 46, 42, -99, 56, -76, -66, -67, -13, 10, 66, 85, -28}))
        ;
        System.out.println(minimumDeletions(new int[]{0, -4, 19, 1, 8, -2, -3, 5}));
        System.out.println(minimumDeletions(new int[]{2, 1, 7, 5, 4, 10, 8, 6}));
        System.out.println(minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6}));
    }

    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans.add(i);
            }
            if (!ans.isEmpty() && nums[i] != target) {
                break;
            }
        }
        return ans;
    }

    public static int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (i - k < 0 || i + k >= n) {
                ans[i] = -1;
                continue;
            }
            ans[i] = (int) ((preSum[i + k + 1] - preSum[i - k]) / (2 * k + 1));
        }
        return ans;


    }

    public static int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1 || n == 2) {
            return n;
        }
        int maxIdx = -1;
        int max = -1;
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIdx = i;
            }
            if (nums[i] > max) {
                max = nums[i];
                maxIdx = i;
            }
        }

        /*
         *  0   1   2   3   4   5   6   7
         *  2   10  7   5   4   1   8   6
         *      ^               ^
         *      1               5
         * */
        /*
         *  0   1   2   3   4   5   6   7
         *  2   1   7   5   4   10  8   6
         *      ^               ^
         *      1               5
         * */

        if (minIdx > maxIdx) {
            int temp = minIdx;
            minIdx = maxIdx;
            maxIdx = temp;
        }

        int len1 = minIdx;
        int len2 = maxIdx - minIdx - 1;
        int len3 = n - maxIdx - 1;
        int ans = Math.min(len1 + len2, Math.min(len3 + len2, len1 + len3)) + 2;

        return ans;
    }
}
