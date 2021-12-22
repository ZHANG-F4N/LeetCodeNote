import java.util.Arrays;
import java.util.HashMap;

public class a350intersect {
    public static void main(String[] args) {
        int[] nums1 = {9, 4, 9, 8, 4};
        int[] nums2 = {4, 9, 5};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMapA = new HashMap<>();
//        HashMap<Integer, Integer> hashMapB = new HashMap<>();
        int temp[] = new int[nums1.length];
        int index = 0;
        for (int i : nums1) {
            hashMapA.put(i, hashMapA.containsKey(i) ? hashMapA.get(i) + 1 : 1);
        }
        for (int i : nums2) {
            if (hashMapA.containsKey(i) && hashMapA.get(i)!=0) {
                temp[index++] = i;
                hashMapA.put(i, hashMapA.get(i) - 1);
            }
        }


//        HashMap<Integer, Integer> hashMapC = hashMapA.size()>hashMapB.size()? hashMapB:hashMapA;
//        int idx = 0;
//        for (Integer key: hashMapA.keySet()) {
//            if (hashMapB.containsKey(key)){
//                for (int i = 0;i< Math.min(hashMapA.get(key),hashMapB.get(key));i++){
//                    temp[idx++] = key;
//                }
//            }
//        }
        int ans[] = Arrays.copyOf(temp,index);

        return ans;
    }
}
