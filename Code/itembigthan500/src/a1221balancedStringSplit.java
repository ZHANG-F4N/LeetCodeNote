public class a1221balancedStringSplit {
    public static void main(String[] args) {
        String s = "RLRRLLRLRL";

        System.out.println(balancedStringSplit(s));

    }

    public static int balancedStringSplit(String s) {
        int count = 0;
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                count++;
            }
            if (s.charAt(i) == 'L') {
                count--;
            }
            if (count == 0) {
                ans++;
            }
        }
        return ans;
    }
}
