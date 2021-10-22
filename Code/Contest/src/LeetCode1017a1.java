public class LeetCode1017a1 {
    public static void main(String[] args) {
        System.out.println(areNumbersAscending("4 5 11 26"));
    }

    public static boolean areNumbersAscending(String s) {
        String[] token = s.split(" ");
        boolean ans = true;

        int before = -1;

        for (int i = 0; i < token.length; i++) {
            String str = token[i];
            if (str.charAt(0) > '9' || str.charAt(0) < '0') {
                continue;
            }
            int now = Integer.parseInt(str);
            if (before >= now) {
                return false;
            }
            before = now;
        }

        return ans;

    }
}
