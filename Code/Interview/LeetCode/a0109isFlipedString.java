public class a0109isFlipedString {
    public static void main(String[] args) {
        System.out.println(isFlipedString("waterbottle", "erbottlewat"));
    }

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        return (s2 + s2).indexOf(s1) != -1;

    }
}
