import java.util.HashMap;
import java.util.Map;

public class a454fourSumCount {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //分组
        HashMap<Integer, Integer> ABHashMap = new HashMap<>();
        int ans = 0;

        for (int a : nums1) {
            for (int b : nums2) {
                ABHashMap.put(a + b, ABHashMap.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                if (ABHashMap.containsKey(0 - c - d)) {
                    ans += ABHashMap.get(0 - c - d);
                }
            }
        }
        return ans;
    }
}
