import java.util.HashMap;
import java.util.HashSet;

public class contest266a1 {
    public static void main(String[] args) {

        System.out.println(countVowelSubstrings("cuaieuouac"));
    }

    public static int countVowelSubstrings(String word) {
        int N = word.length();
        int ans = 0;
        char[] arr = word.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for (int i = 0; i < N; i++) {
            hashMap.put('a', 1);
            hashMap.put('e', 1);
            hashMap.put('i', 1);
            hashMap.put('o', 1);
            hashMap.put('u', 1);
            for (int j = i; j < N; j++) {
                char ch = arr[j];
                if (!set.contains(ch)) {
                    break;
                } else {
                    if (hashMap.get(ch) == 1) {
                        hashMap.put(ch, 0);
                    }
                }
                int num = hashMap.get('a') + hashMap.get('e') + hashMap.get('i') + hashMap.get('o') + hashMap.get('u');
                if (num == 0) {
                    ans++;
                }
            }
        }
        return ans;

    }
}
