import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class a0808permutation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(permutation("OSS")));
        System.out.println(Arrays.toString(permutation("qqe")));
        System.out.println(Arrays.toString(permutation("qwe")));
    }

    public static String[] permutation(String S) {
        HashSet<String> set = new HashSet<>();
        LinkedList<String> ans = new LinkedList<>();
        dfs(S.toCharArray(), 0, ans, set);
        return ans.toArray(new String[ans.size()]);
    }

    public static void dfs(char[] arr, int i, LinkedList<String> ans, HashSet<String> set) {
        if (i == arr.length) {
            String temp = String.valueOf(arr);
            if (!set.contains(temp)) ans.addLast(temp);
            set.add(temp);
            return;
        }
        for (int j = i; j < arr.length; j++) {
            if (i != j && arr[i] == arr[j]) continue;
            swap(arr, i, j);
            dfs(arr, i + 1, ans, set);
            swap(arr, i, j);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }
}
