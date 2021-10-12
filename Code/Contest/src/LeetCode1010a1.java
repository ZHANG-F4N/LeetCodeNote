import java.util.*;

public class LeetCode1010a1 {
    public static void main(String[] args) {
        int[] nums1 = {1,1,3,2};
        int[] nums2 = {2, 3};
        int[] nums3 = {3};



        twoOutOfThree(nums1, nums2, nums3);
    }

    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        HashSet<Integer> set3 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        for (int i = 0; i < nums3.length; i++) {
            set3.add(nums3[i]);
        }
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();


        Iterator<Integer> it = set1.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
        }
        it = set2.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
        }
        it = set3.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (en.getValue() >= 2) {
                ans.add(en.getKey());
            }
        }
        return ans;
    }
}
