import java.util.HashMap;
import java.util.HashSet;

public class contest266a2 {
    public static void main(String[] args) {
        System.out.println(countVowels("noosabasboosa"));
        System.out.println(countVowels("cuaieuouac"));
    }

    public static long countVowels(String word) {
        long ans = 0;
        char[] chars = word.toCharArray();
        int L = word.length();
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        //bba ba a
        //bbac bac ac
        //  b   b   a   c
        //  0   1   2   3
        //  0   0
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                ans += (long)(i+1) * (L - i);
            }
        }
        return ans;

    }
}
