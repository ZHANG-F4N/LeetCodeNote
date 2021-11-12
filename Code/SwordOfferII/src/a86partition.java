import java.util.ArrayList;
import java.util.List;

public class a86partition {
    public static void main(String[] args) {
        System.out.println(partition("google"));
    }

    public static String[][] partition(String s) {

        List<List<String>> ans = new ArrayList<>();

        traceback(ans, new ArrayList<>(), s, 0);


        return null;

    }

    public static void traceback(List<List<String>> ans, List<String> list, String str, int idx) {
        if (idx == str.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        String sub = str.substring(idx, idx + 1);
        list.add(sub);
        traceback(ans, list, str, idx + 1);
        list.remove(list.size() - 1);
        String temp = sub;
        for (int i = list.size() - 1; i >= 0; i--) {
            temp = list.get(i) + temp;
            if (judge(temp)){

            }

        }
    }

    public static boolean judge(String str) {
        if (str.length() == 0) {
            return true;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
