import java.util.Arrays;
import java.util.HashSet;

public class contest298 {
    public static void main(String[] args) {
        System.out.println(greatestLetter("arRAzFif"));
        System.out.println(greatestLetter("lEeTcOdE"));
    }

    //Q1
    public static String greatestLetter(String s) {
        int[] low = new int[26];
        int[] big = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                big[c - 'A'] = 1;
            }
            if (Character.isLowerCase(c)) {
                low[c - 'a'] = 1;
            }
        }
        for (int i = 25; i >= 0; i--) {
            if (low[i] != 0 && big[i] != 0) return "" + (char) (i + 'A');
        }
        return "";
    }

    //Q2
    public static int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        if (num < k) return -1;
        for (int i = 1; i <= num; i++) {
            if (num - i * k < 0) break;
            if ((num - i * k) % 10 == 0) return i;

        }
        return -1;
    }

}
