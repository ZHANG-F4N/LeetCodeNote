import java.util.Arrays;

public class a462minMoves2 {
    public static void main(String[] args) {
        System.out.println(minMoves2(new int[]{1, 10, 2, 9}));
    }

    public static int minMoves2(int[] nums) {
        int n = nums.length;
//        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int val = nums[n >> 1];
        int ans = 0;

        for (int num : nums) {
            ans += Math.abs(num - val);
        }

        return ans;

//        for (int i = 0; i < n; i++) {
//            if (nums[i] > r) r = nums[i];
//            if (nums[i] < l) l = nums[i];
//        }
//        long ans = check(nums, mid);
//        ;
//
//        while (l < r) {
//            int mid = l + (r >> 1);
//            int val = check(nums, mid);
//
//
//        }
//        return (int) ans;
    }

//    public static int check(int[] nums, int mid) {
//        long ans = 0;
//
//        for (int num : nums) {
//            ans += Math.abs(num - mid);
//        }
//
//        return (int) ans;
//    }
}
