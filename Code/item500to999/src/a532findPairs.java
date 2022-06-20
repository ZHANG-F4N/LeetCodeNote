import java.util.HashMap;
import java.util.HashSet;

public class a532findPairs {
    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{1, 2, 4, 4, 3, 3, 0, 9, 2, 3}, 3));
    }

    public static int findPairs(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i] + k) && hashMap.get(nums[i] + k) > i)
                set.add(nums[i]);
            if (hashMap.containsKey(nums[i] - k) && hashMap.get(nums[i] - k) > i)
                set.add(nums[i] - k);
        }
        return set.size();
    }
}
