import java.util.HashMap;
import java.util.HashSet;

public class a219containsNearbyDuplicate {
    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 2, 3}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) return true;
            hashSet.add(nums[i]);
            if (hashSet.size() > k) {
                hashSet.remove(nums[i - k]);
            }
        }
        return false;
    }
}
