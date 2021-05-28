import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class a387firstUniqChar {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
    public static int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(chars[s.charAt(i)-'a'] == 1){
                return i;
            }
        }
        return -1;
    }
}
