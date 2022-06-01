import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class a1711findClosest {
    public static void main(String[] args) {

    }

    public static int findClosest(String[] words, String word1, String word2) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list;
            if (map.containsKey(words[i])) {
                list = map.get(words[i]);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(words[i], list);
        }

        List<Integer> wordIdx1 = map.get(word1);
        List<Integer> wordIdx2 = map.get(word2);

        int ans = Math.abs(wordIdx1.get(0) - wordIdx2.get(0));

        int l = 0, r = 0;
        while (l < wordIdx1.size() && r < wordIdx2.size()) {
            ans = Math.min(ans, Math.abs(wordIdx1.get(l) - wordIdx2.get(r)));
            if (wordIdx1.get(l) < wordIdx2.get(r)) {
                l++;
            } else {
                r++;
            }
        }
        
        return ans;


    }
}
