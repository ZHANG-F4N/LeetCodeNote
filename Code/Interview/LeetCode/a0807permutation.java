import java.util.*;

public class a0807permutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation2("qwe")));
    }

    public static String[] permutation(String S) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : S.toCharArray()) {
            deque.addLast(c);
        }
        ArrayList<String> ans = new ArrayList<>();
        backtrace(ans, deque, new StringBuilder());
        return ans.toArray(new String[ans.size()]);
    }

    public static void backtrace(ArrayList<String> ans, Deque<Character> deque,
                                 StringBuilder temp) {
        if (deque.isEmpty()) {
            ans.add(temp.toString());
            return;
        }
        for (int i = 0; i < deque.size(); i++) {
            Character ch = deque.pollFirst();
            temp.append(ch);
            backtrace(ans, deque, temp);
            temp.deleteCharAt(temp.length() - 1);
            deque.offerLast(ch);
        }
    }

    public static String[] permutation2(String S) {
        LinkedList<String> ans = new LinkedList<>();
        dfs(S.toCharArray(), 0, ans);
        return ans.toArray(new String[ans.size()]);
    }

    // 第 i 个数和它后面的数进行交换
    public static void dfs(char[] temp, int i, LinkedList<String> ans) {
        if (i == temp.length) {
            ans.addLast(String.valueOf(temp));
            return;
        }
        for (int j = i; j < temp.length; j++) {
            swap(temp, i, j);
            dfs(temp, i+1 , ans);
            swap(temp, i, j);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }
}
