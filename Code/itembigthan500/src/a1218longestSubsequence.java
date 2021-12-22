import java.util.HashMap;

public class a1218longestSubsequence {
    public static void main(String[] args) {
//        System.out.println(longestSubsequence(new int[]{1, 2, 3, 4}, 1));
//        System.out.println(longestSubsequence(new int[]{1, 3, 5, 7}, 1));
        System.out.println(longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }

    public static int longestSubsequence(int[] arr, int difference) {
        int max = 1;
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int beg = arr[i];
            int pre = beg - difference;
            int len = hashMap.getOrDefault(pre, 0) + 1;
            hashMap.put(beg, len);
            max = len > max ? len : max;
        }
        return max;

    }
}
