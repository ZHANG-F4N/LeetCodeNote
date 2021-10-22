import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class a38permutation {
    public static void main(String[] args) {
        System.out.println(permutation("abc"));
    }

    public static String[] permutation(String s) {
        char[] arr = s.toCharArray();

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            arr[i] = '#';
            backtrace(set, arr, new StringBuilder().append(ch));
            arr[i] = ch;
        }


        int N = set.size();
        String[] ans = new String[N];

        Iterator<String> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            ans[i++] = it.next();
        }
        return ans;
    }

    public static void backtrace(HashSet<String> set, char[] arr, StringBuilder temp) {
        if (temp.length() == arr.length) {
            set.add(temp.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '#') {
                temp.append(arr[i]);
                char ch = arr[i];
                arr[i] = '#';
                backtrace(set, arr, temp);
                arr[i] = ch;
                temp.deleteCharAt(temp.length() - 1);
            }
        }


    }
}
