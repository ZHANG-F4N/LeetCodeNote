import java.util.HashSet;

public class a1044longestDupSubstring {
    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
    }

    public static long[] h = new long[3 * 10000 + 10];
    public static long[] p = new long[3 * 10000 + 10];

    public static String longestDupSubstring(String s) {
        // 哈希数组
        int P = 131313;
        // 次方数组
        p[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            // 哈希函数 自然溢出
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            // 记录前缀，用以还原字符串
            p[i] = p[i - 1] * P;
        }

        int l = 0, r = s.length();
        String ans = "";
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            String t = check(s, mid);
            if (t.equals("")) r = mid - 1;
            else l = mid+1;
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }

    public static String check(String str, int len) {
        int n = str.length();
        HashSet<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long hash = h[j] - h[i - 1] * p[len];
            if (set.contains(hash)) return str.substring(i-1, j);
            set.add(hash);
        }
        return "";
    }
}
