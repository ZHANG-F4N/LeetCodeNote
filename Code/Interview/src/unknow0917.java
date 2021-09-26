import java.util.Arrays;

public class unknow0917 {
    public static void main(String[] args) {
        //长度不超过k的递增子序列的个数

        int[] nums = {1, 2, 3, 4, 2};
//        int[] nums = {1,2,3};
        System.out.println(findNumberOfLIS(nums, 3));
    }

    public static int findNumberOfLIS(int[] nums, int k) {
        int N = nums.length;
        if (N <= 1) return N;
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        int ans = 0;
        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < j; ++i)
                if (nums[i] < nums[j]) {
                    if (lengths[i] <= k) {
                        ans += lengths[i] * 3;
                    } else if (lengths[i] == k) {
                        ans += lengths[i] * 2;
                    }
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        counts[j] += counts[i];
                    }
                }
        }

//        int longest = 0, ans = 0;

//        for (int length : lengths) {
//            longest = Math.max(longest, length);
//        }
//        for (int i = 0; i < N; ++i) {
//            if (lengths[i] <= k) {
//                ans += counts[i];
//            }
//        }
        return ans;
    }

}
