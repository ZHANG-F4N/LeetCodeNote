import java.io.CharArrayWriter;
import java.util.HashMap;

public class a48lengthOfLongestSubstring {
    public static void main(String[] args) {
        String str = "abba";
        System.out.println(lengthOfLongestSubstring(str));
    }

    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character, Integer> hashMap = new HashMap<>();
        if (s.length() == 0) {
            return 0;
        }

        int max = 1;
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            if (hashMap.containsKey(ch)) {
                left = Math.max(hashMap.get(ch) + 1, left);
                hashMap.replace(ch, right);
            } else {
                hashMap.put(ch, right);
            }
            max = max > right - left + 1 ? max : right - left + 1;
            right++;
        }
        return max;
    }
}
