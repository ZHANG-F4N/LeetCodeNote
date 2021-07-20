import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class a287findDuplicate {
    public static void main(String[] args) {
        //int[] nums = {1, 3, 4, 2, 2};
        int[] nums = {2,5,9,6,9,3,8,9,7,1};
        System.out.println(findDuplicate(nums));
    }

    public static int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);
        //慢指针回到头部
        //快指针在环内循环,一次一步
        //慢指针走到环入口,快指针也到入口
        slow = 0;

        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
//        //HashMap
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int num : nums) {
//            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
//        }
//        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
//            if (entry.getValue() > 1) {
//                return entry.getKey();
//            }
//        }
//        return 0;
    }
}
