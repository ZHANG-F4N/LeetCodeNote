import java.util.*;

public class contest66a1 {
    public static void main(String[] args) {
        System.out.println(countWords(new String[]{"leetcode", "is", "amazing", "as", "is"}, new String[]{"amazing", "leetcode", "is"}));
    }

    public static int countWords(String[] words1, String[] words2) {
        HashMap<String, Integer> hashMap1 = new HashMap<>();
        HashMap<String, Integer> hashMap2 = new HashMap<>();
        for (String s : words1) {
            hashMap1.put(s, hashMap1.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            hashMap2.put(s, hashMap2.getOrDefault(s, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<String, Integer> en : hashMap1.entrySet()) {
            if (en.getValue() == 1 && hashMap2.getOrDefault(en.getKey(), 0) == 1) {
                ans++;
            }
        }
        return ans;
    }
}
