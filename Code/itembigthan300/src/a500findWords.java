import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class a500findWords {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }

    public static String[] findWords(String[] words) {
        char[] line1 = "qwertyuiop".toCharArray();
        char[] line2 = "asdfghjkl".toCharArray();
        char[] line3 = "zxcvbnm".toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : line1) {
            map.put(c, 1);
        }
        for (char c : line2) {
            map.put(c, 2);
        }
        for (char c : line3) {
            map.put(c, 3);
        }
        ArrayList<String> list = new ArrayList<>();
        for (String str : words) {
            String word = str.toLowerCase();
            int line = map.get(word.charAt(0));
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (line != map.get(c)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                list.add(str);
            }
        }
        return list.toArray(new String[list.size()]);

    }
}
