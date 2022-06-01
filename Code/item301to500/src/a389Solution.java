import java.util.*;

public class a389Solution {
    static class Solution {
        private HashMap<Integer, List<Integer>> map;

        public Solution(int[] nums) {
            map = new HashMap<>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                List<Integer> list;
                if (map.containsKey(nums[i])) list = map.get(nums[i]);
                else {
                    list = new ArrayList<>();
                    map.put(nums[i], list);
                }
                list.add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            int random = new Random().nextInt(list.size());
            return list.get(random);
        }
    }

    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{1, 2, 3, 3, 3});
        int param_1 = obj.pick(3);
    }
}
