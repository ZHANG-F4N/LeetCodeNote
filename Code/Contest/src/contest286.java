import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class contest286 {
    public static void main(String[] args) {
//        findDifference(new int[]{1, 2, 3}, new int[]{2, 4, 6});
        System.out.println(minDeletion(new int[]{1, 1, 2, 3, 5}));
    }

    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        List ans1 = new ArrayList();
        List ans2 = new ArrayList();
        for (Integer integer : set1) {
            if (!set2.contains(integer)) ans1.add(integer);
        }
        for (Integer integer : set2) {
            if (!set1.contains(integer)) ans2.add(integer);
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(ans1);
        ans.add(ans2);
        return ans;
    }

    public static int minDeletion(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 0) return 0;
        return 0;


    }


}
