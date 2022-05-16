import java.util.*;

public class contest293 {
    public static void main(String[] args) {
//        removeAnagrams(new String[]{"abba", "baba", "bbaa", "cd", "cd"});
        largestCombination(new int[]{16, 17, 71, 62, 12, 24, 14});
    }


    // Q1
    public static List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        char[] chars = words[0].toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        ans.add(words[0]);
        for (int i = 1; i < n; i++) {
            char[] chars1 = words[i].toCharArray();
            HashMap<Character, Integer> tmap = new HashMap<>();
            for (int j = 0; j < chars1.length; j++) {
                tmap.put(chars1[j], tmap.getOrDefault(chars1[j], 0) + 1);
            }
            if (tmap.size() != map.size()) {
                map = tmap;
                ans.add(words[i]);
            } else {
                for (Map.Entry<Character, Integer> en : tmap.entrySet()) {
                    if (en.getValue() != map.get(en.getKey())) {
                        map = tmap;
                        ans.add(words[i]);
                        break;
                    }
                }
            }
        }

        return ans;


    }

    //Q2
    public static int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0;


        int n = special.length;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, special[i] - special[i - 1] - 1);
        }
        max = Math.max(special[0] - bottom, max);
        max = Math.max(top - special[n - 1], max);
        return max;

    }

    //Q3
    public static int largestCombination(int[] candidates) {

        int n = candidates.length;
        int[] val = new int[32];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                int num = candidates[i];
                if ((num & (1 << j)) != 0) {
                    val[j]++;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < 32; i++) {
            max = Math.max(max, val[i]);
        }

        return max;
    }
}
