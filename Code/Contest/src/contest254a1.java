public class contest254a1 {
    public static void main(String[] args) {
        System.out.println(numOfStrings(new String[]{"a", "abc", "bc", "d"}, "abc"));
    }

    public static int numOfStrings(String[] patterns, String word) {

        int ans = 0;
        for (String pattern : patterns) {
            if (word.indexOf(pattern) != -1) ans++;
        }
        return ans;
    }
}
