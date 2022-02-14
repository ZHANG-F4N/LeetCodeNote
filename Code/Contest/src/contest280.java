import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class contest280 {
    public static void main(String[] args) {
//        System.out.println(minimumOperations(new int[]{2,2}));
//        System.out.println(minimumOperations(new int[]{69, 91, 47, 74, 75, 94, 22, 100, 43, 50, 82, 47, 40, 51, 90, 27, 98, 85, 47, 14, 55, 82, 52, 9, 65, 90, 86, 45, 52, 52, 95, 40, 85, 3, 46, 77, 16, 59, 32, 22, 41, 87, 89, 78, 59, 78, 34, 26, 71, 9, 82, 68, 80, 74, 100, 6, 10, 53, 84, 80, 7, 87, 3, 82, 26, 26, 14, 37, 26, 58, 96, 73, 41, 2, 79, 43, 56, 74, 30, 71, 6, 100, 72, 93, 83, 40, 28, 79, 24}));

        System.out.println(minimumRemoval(new int[]{4, 1, 6, 5}));
        System.out.println(minimumRemoval(new int[]{2, 10, 3, 2}));
    }

    // q3
    public static long minimumRemoval(int[] beans) {
        long ans = Long.MAX_VALUE;
        long total = 0;
        int n = beans.length;
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        Arrays.sort(beans);
        for (int i = 0; i < n; i++) {
            long temp = 0;
            temp = total - (long)(n - i) * (long)beans[i];
            ans = Math.min(ans,temp);
        }
        return ans;
    }

    //q2
    public static int minimumOperations(int[] nums) {
        int ans = 0;
        int n = nums.length;
        if (n == 1) return 0;
        HashMap<Integer, Integer> hashMap0 = new HashMap<>();
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) {
                hashMap1.put(nums[i], hashMap1.getOrDefault(nums[i], 0) + 1);
            } else {
                hashMap0.put(nums[i], hashMap0.getOrDefault(nums[i], 0) + 1);
            }
        }
        int[] top0 = {-1, 0};
        int[] top1 = {-1, 0};
        for (Map.Entry<Integer, Integer> en : hashMap0.entrySet()) {
            if (en.getValue() > top0[1]) {
                top1[0] = top0[0];
                top1[1] = top0[1];
                top0[0] = en.getKey();
                top0[1] = en.getValue();

            } else if (en.getValue() > top1[1]) {
                top1[0] = en.getKey();
                top1[1] = en.getValue();
            }
        }
        int[] top00 = {-1, 0};
        int[] top01 = {-1, 0};
        for (Map.Entry<Integer, Integer> en : hashMap1.entrySet()) {
            if (en.getValue() > top00[1]) {
                top01[0] = top00[0];
                top01[1] = top00[1];
                top00[0] = en.getKey();
                top00[1] = en.getValue();
            } else if (en.getValue() > top01[1]) {
                top01[0] = en.getKey();
                top01[1] = en.getValue();
            }
        }
        int even = n >> 1;
        int odd = (n >> 1) + (n & 1);
        if (top0[0] != top00[0]) {
            ans = even - top0[1] + odd - top00[1];
//            ans = n % 2 == 0 ? (n >> 1) - top0[1] + (n >> 1) - top00[1] : (n >> 1) - top0[1] + (n >> 1) - top00[1] + 1;
        } else {
            if (top0[1] > top00[1]) {
                ans = even - top0[1] + odd - top01[1];
            } else {
                ans = even - top1[1] + odd - top00[1];
            }
        }
        return ans;
    }

}
