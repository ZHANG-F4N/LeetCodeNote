import java.util.HashMap;

public class a1248numberOfSubarrays {
    public static void main(String[] args) {
        int[] num = {2,2,2,1,2,2,1,2,2,2};
        System.out.println(numberOfSubarrays(num, 2));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] pre = new int[N];

        pre[0] = nums[0] % 2 == 0 ? 0 : 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] % 2 == 0) {
                pre[i] = pre[i - 1];
            } else {
                pre[i] = pre[i - 1] + 1;
            }
        }

        int ans = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(k, 1);

        for (int i = 0; i < N; i++) {
            ans += hashMap.getOrDefault(pre[i], 0);
            hashMap.put(pre[i] + k, hashMap.getOrDefault(pre[i] + k, 0) + 1);
        }

        return ans;
    }
}
