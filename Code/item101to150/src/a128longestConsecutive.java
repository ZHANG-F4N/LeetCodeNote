import java.util.HashSet;
import java.util.Iterator;

public class a128longestConsecutive {
    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        int ans = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            int tempLong = 0;
            if (!hashSet.contains(val - 1)) {
                do {
                    tempLong++;
                    val++;
                } while (hashSet.contains(val));
            }
            if (tempLong > ans) {
                ans = tempLong;
            }
        }
        return ans;
    }
}
