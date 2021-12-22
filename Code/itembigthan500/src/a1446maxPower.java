import java.awt.event.MouseAdapter;

public class a1446maxPower {
    public static void main(String[] args) {
        System.out.println(maxPower("cc"));
        System.out.println(maxPower("abbcccddddeeeeedcba"));
    }

    public static int maxPower(String s) {
        int[] sCnt = new int[26];
        char[] str = s.toCharArray();
        char pre = str[0];
        int ans = 1;
        sCnt[pre - 'a']++;
        int len = 1;
        for (int i = 1; i < str.length; i++) {
            char ch = str[i];
            if (ch == pre) {
                len++;
            } else {
                sCnt[pre - 'a'] = Math.max(sCnt[pre - 'a'], len);
                ans = Math.max(ans, sCnt[pre - 'a']);
                pre = ch;
                len = 1;
            }
        }
        sCnt[pre - 'a'] = Math.max(sCnt[pre - 'a'], len);
        ans = Math.max(ans, sCnt[pre - 'a']);

        return ans;

    }
}
