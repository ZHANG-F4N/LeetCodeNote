import java.util.*;

public class LeetCode1030a1 {
    public static void main(String[] args) {
        System.out.println(kthDistinct(new String[]{"d", "b", "c", "b", "c", "a"}, 2));
    }

    public static String kthDistinct(String[] arr, int k) {


        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        for (String s : arr) {
            hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
        }
        int idx = 0;
        for (String s : arr) {
            if (hashMap.containsKey(s) && hashMap.get(s) == 1) {
                idx++;
                if (idx == k) {
                    return s;
                }
            }
        }
        return "";


    }
}
