import java.util.Arrays;

public class a821shortestToChar {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestToChar("abaa", 'b')));
        System.out.println(Arrays.toString(shortestToChar("aaba", 'b')));
        System.out.println(Arrays.toString(shortestToChar("aaab", 'b')));
        System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e')));
    }

    public static int[] shortestToChar(String s, char c) {
        int l = -s.length();
        int r = s.indexOf(c);
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (r == i) {
                l = r;
                while (r + 1 < n && chars[++r] != c) ;
            }
            if (r + 1 == n && chars[r] != c) {
                ans[i] = Math.abs(l - i);
                r+=s.length();
                continue;
            }
            ans[i] = Math.min(Math.abs(l - i), Math.abs(r - i));
        }
        return ans;
    }
}
