public class a45jump {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        int ans = 1;
        int N = nums.length;
        if (N == 1) {
            return 0;
        }
        int MaxStep = nums[0];
        int nowStep = 0;

        while (nowStep < N - 1 && MaxStep < N - 1) {
            int tempMax = nowStep + nums[nowStep];
            for (int j = nowStep + 1; j <= tempMax && j < N - 1; j++) {
                if (j + nums[j] > MaxStep) {
                    MaxStep = j + nums[j];
                    nowStep = j;
                }
            }
            ans++;
            if (MaxStep >= N - 1) {
                return ans;
            }
        }
        return ans;
    }
}
