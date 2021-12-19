import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class contest272 {
    public static void main(String[] args) {
//        System.out.println(firstPalindrome(new String[]{"notapalindrome", "racecar"}));
//        System.out.println(addSpaces("LeetcodeHelpsMeLearn", new int[]{0, 8, 13, 15}));
//        System.out.println(getDescentPeriods(new int[]{3, 2, 1, 4}));
        System.out.println(kIncreasing(new int[]{4, 1, 5, 2, 6, 2}, 2));
    }

    public static String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            StringBuilder stringBuilder = new StringBuilder(words[i]);

            String reverse = stringBuilder.reverse().toString();
            if (reverse.equals(words[i])) return words[i];

        }
        return "";
    }

    public static String addSpaces(String s, int[] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = spaces[i] + i;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < spaces.length; i++) {
            sb.insert(spaces[i], ' ');
        }
        return sb.toString();
    }

    public static long getDescentPeriods(int[] prices) {
        //  3   2   1   4
        // l r
        int l = 0, r = 0;
        long ans = 0;
        while (r < prices.length) {
            if (r + 1 < prices.length && prices[r] - prices[r + 1] == 1) {
                r++;
                continue;
            }
            int len = r - l + 1;
            ans += (1 + len) * (long) len >> 1;
            r++;
            l = r;
        }
        return ans;
    }

    public static int kIncreasing(int[] arr, int k) {

        int ans = 0;
        //HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[i - k]) {
                ans++;
                arr[i] = arr[i - k];
            }
        }
        return ans;

    }
}
