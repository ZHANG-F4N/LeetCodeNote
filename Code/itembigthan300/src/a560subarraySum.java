import java.util.HashMap;

public class a560subarraySum {
    public static void main(String[] args) {
        int[] num = {1, 2, 3};
        System.out.println(subarraySum2(num, 3));
    }

    //n^2
    public static int subarraySum(int[] nums, int k) {

        int ans = 0;

        int N = nums.length;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int subarraySum2(int[] nums, int k) {

        int ans = 0;
        int N = nums.length;
        //      pre[i] - pre[j-1] == k
        // =>   pre[i] == k + pre[j-1]
        // =>   pre[j-1] == pre[i] - k

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int preSum =0;
        // 这相当于 保存一个 前缀和是0的位置, 就相当于 没有数时的和 0+k
        hashMap.put(k, 1);
        for (int i = 0; i < N; i++) {
            preSum +=  nums[i];
            ans += hashMap.getOrDefault(preSum, 0);
            hashMap.put(preSum + k, hashMap.getOrDefault(preSum + k, 0) + 1);
        }


        return ans;
    }


}
