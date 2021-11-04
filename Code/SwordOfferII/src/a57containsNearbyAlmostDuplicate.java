import java.util.HashMap;

public class a57containsNearbyAlmostDuplicate {
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    // 我们可以设定桶的大小为 t + 1 。如果两个元素同属一个桶，那么这两个元素必然符合条件。
    // 如果两个元素属于相邻桶，那么我们需要校验这两个元素是否差值不超过 t。
    // 如果两个元素既不属于同一个桶，也不属于相邻桶，那么这两个元素必然不符合条件。
    // 由于一个桶中最多只有一个元素（两个元素直接返回true)，用HashMap即可。
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        HashMap<Long, Long> hashMap = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], w);
            if (hashMap.containsKey(id)) {
                return true;
            }
            if (hashMap.containsKey(id - 1) && Math.abs(nums[i] - hashMap.get(id - 1)) < w) {
                return true;
            }
            if (hashMap.containsKey(id + 1) && Math.abs(nums[i] - hashMap.get(id + 1)) < w) {
                return true;
            }
            hashMap.put(id, (long) nums[i]);
            if (i >= k) {
                hashMap.remove(getId(nums[i - k], w));
            }
        }
        return false;
    }

    public static long getId(long val, Long t) {
        if (val >= 0) {
            return val / t;
        }
        return (val + 1) / t - 1;
    }
}
