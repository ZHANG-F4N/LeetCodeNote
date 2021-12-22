public class a686repeatedStringMatch {
    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abc", "cabcabca"));
        System.out.println(repeatedStringMatch("abc", "ca"));
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
    }

    public static int repeatedStringMatch(String a, String b) {
        //int ans = -1;
        /*
         *
         *
         * */

        int n = a.length();
        int m = b.length();
        int num = m / n;
        if (num == 0) num = 1;
        StringBuilder stringBuilder = new StringBuilder(a);
        for (int i = 0; i < num - 1; i++) {
            stringBuilder.append(a);
        }
        for (int i = num; i <= num + 2; i++) {
            if (stringBuilder.lastIndexOf(b) != -1) return i;
            stringBuilder.append(a);
        }
//        if (stringBuilder.lastIndexOf(b) != -1) return num;
//        stringBuilder.append(a);
//        if (stringBuilder.lastIndexOf(b) != -1) return num + 1;

        return -1;
    }
}
