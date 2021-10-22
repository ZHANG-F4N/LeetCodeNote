public class a01_01isUnique {
    public static void main(String[] args) {
        System.out.println(isUnique("leetcode"));
    }

    public static boolean isUnique(String astr) {
        int flag = 0;

        for (int i = 0; i < astr.length(); i++) {
            char ch = astr.charAt(i);
            int dist = ch - 'a';
            if ((flag & (1 << dist)) != 0) {
                return false;
            }
            flag |= 1 << dist;
        }
        return true;
    }
}
