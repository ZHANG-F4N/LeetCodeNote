import java.util.HashSet;

public class contest250a1 {
    public static void main(String[] args) {
        System.out.println(canBeTypedWords("hello world", "ad"));
    }

    public static int canBeTypedWords(String text, String brokenLetters) {
        HashSet<Character> set = new HashSet<>();
        for (char c : brokenLetters.toCharArray()) {
            set.add(c);
        }
        int ans = 0;
        for (String s : text.split(" ")) {
            boolean flag = true;
            for (char c : s.toCharArray()) {
                if (set.contains(c)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }
}
