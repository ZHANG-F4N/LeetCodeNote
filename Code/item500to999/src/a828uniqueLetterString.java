import java.util.Arrays;

public class a828uniqueLetterString {
    public static void main(String[] args) {
        System.out.println(uniqueLetterString("LEETCODE"));
    }

    public static int uniqueLetterString(String s) {
        int[] map = new int[26];
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] l = new int[n], r = new int[n];
        Arrays.fill(map, -1);
        for (int i = 0; i < chars.length; i++) {
            l[i] = map[chars[i] - 'A'];
            map[chars[i] - 'A'] = i;
        }
        Arrays.fill(map, n);
        for (int i = n - 1; i >= 0; i--) {
            r[i] = map[chars[i] - 'A'];
            map[chars[i] - 'A'] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (i - l[i]) * (r[i] - i);
        }
        return ans;

    }
}
