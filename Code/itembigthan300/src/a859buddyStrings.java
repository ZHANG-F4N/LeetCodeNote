import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class a859buddyStrings {
    public static void main(String[] args) {
        System.out.println(buddyStrings("ab", "ba"));
        System.out.println(buddyStrings("ab", "ab"));
        System.out.println(buddyStrings("aa", "aa"));
        System.out.println(buddyStrings("aaaaaaabc", "aaaaaaacb"));
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            boolean[] flag = new boolean[26];
            for (char c : s.toCharArray()) {
                if (flag[c - 'a']) {
                    return true;
                }
                flag[c - 'a'] = true;
            }
        }
        int n = s.length();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                list.add(i);
            }
            if (list.size() >= 3) {
                return false;
            }
        }
        if (list.size() < 2) {
            return false;
        }
        if (s.charAt(list.get(0)) == goal.charAt(list.get(1)) && s.charAt(list.get(1)) == goal.charAt(list.get(0))) {
            return true;
        }
        return false;


    }
}
