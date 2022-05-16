import java.util.HashSet;

public class a944minDeletionSize {
    public static void main(String[] args) {
        System.out.println(minDeletionSize(new String[]{"a", "b"}));
        System.out.println(minDeletionSize(new String[]{"cba", "daf", "ghi"}));
    }

    public static int minDeletionSize(String[] strs) {
        int n = strs.length;
        if (n == 1) return 0;
        HashSet<Integer> col = new HashSet<>();
        int m = strs[0].length();
        int ans = 0;
        loop:
        for (int i = 0; i < m; i++) {
            for (int j = 0, cur = -1; j < n; j++) {
                int t = (int) strs[j].charAt(i);
                if (t < cur && ++ans >= 0) continue loop;
                cur = t;
            }
        }
        return ans;
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < m; j++) {
//                if (col.contains(j)) continue;
//                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
//                    col.add(j);
//                }
//            }
//        }
//        return col.size();
    }
}
