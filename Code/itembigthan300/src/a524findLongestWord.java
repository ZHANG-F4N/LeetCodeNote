import java.util.*;

public class a524findLongestWord {
    public static void main(String[] args) {
        String s = "abpcplea";
//        String s = "abce";
//        String[] dictionary = {"ale", "apple", "monkey", "plea"};
//        String[] dictionary = {"a", "b", "c"};
//        String[] dictionary = {"abe", "abc"};
        String[] dictionary = {"ale", "apple", "monkey", "plea", "abpcplaaa", "abpcllllll", "abccclllpppeeaaaa"};
        List<String> strings = new ArrayList<>();

        for (String s1 : dictionary) {
            strings.add(s1);
        }

        System.out.println(judge("abpcplea","abpcplaaa"));
        System.out.println(findLongestWord(s, strings));
    }

    public static String findLongestWord(String s, List<String> dictionary) {

        String ans = "{";
        for (int i = 0; i < dictionary.size(); i++) {
            String str = dictionary.get(i);
            if (judge(s, str)) {

                if (ans.length() < str.length()) {
                    ans = str;
                    continue;
                }
                if (ans.length() == str.length() && ans.compareTo(str) > 0) {
                    ans = str;
                }
            }
        }
        if (ans.equals("{")) {
            return "";
        }
        return ans;


    }

    public static boolean judge(String str, String sub) {
        int index1 = 0;
        int index2 = 0;

        int M = str.length() - 1;
        int N = sub.length() - 1;
        while (index1 <= M && index2 <= N) {
            if (str.charAt(index1) == sub.charAt(index2)) {
                index1++;
                index2++;
                continue;
            }
            while (index1 <= M && str.charAt(index1) != sub.charAt(index2)) {
                index1++;
            }
            if (index1 > M && index2 <= N) {
                return false;
            }
        }
        if (index1 > M && index2 <= N) {
            return false;
        }

        return true;

    }
}
