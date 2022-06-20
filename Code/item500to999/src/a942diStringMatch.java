public class a942diStringMatch {
    public static void main(String[] args) {
        System.out.println(diStringMatch("DDI"));
        System.out.println(diStringMatch("III"));
        System.out.println(diStringMatch("IDID"));
    }

    public static int[] diStringMatch(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] ans = new int[n + 1];
        int val = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'I') {
                ans[i] = val;
                val++;
            }
        }
        ans[n] = val++;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == 'D') {
                ans[i] = val;
                val++;
            }
        }


        return ans;
    }
}
