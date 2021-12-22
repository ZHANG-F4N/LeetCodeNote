import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntToDoubleFunction;

public class a368largestDivisibleSubset {
    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{3}));
        System.out.println(largestDivisibleSubset(new int[]{3, 17}));
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 3}));
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int[] dp = new int[nums.length];
        int[] path = new int[nums.length];
        int idx = 0;
        int max = 1;
        Arrays.sort(nums);
        dp[0] = 1;
        path[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int len = 1;
            int prev = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > len) {
                        prev = j;
                        len = dp[j] + 1;
                    }
                }
            }
            path[i] = prev;
            dp[i] = len;
            if (len > max) {
                idx = i;
                max = len;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() != max) {
            ans.add(0, nums[idx]);
            idx = path[idx];
        }
        return ans;


    }
}
