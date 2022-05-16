import java.util.HashMap;
import java.util.HashSet;

public class contest291 {
    public static void main(String[] args) {
//        System.out.println(removeDigit("123", '3'));
    }

    public static String removeDigit(String number, char digit) {
        char[] chars = number.toCharArray();
        String val = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == digit) {
                String h = number.substring(0, i);
                String l = number.substring(i + 1);
                if (val.compareTo(h + l) < 0) {
                    val = h + l;
                }
            }
        }
        return val;


    }

    //Q2
    public static int minimumCardPickup(int[] cards) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int l = 0, r = 0, n = cards.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(cards[i])) {
                ans = Math.min(i - map.get(cards[i]) + 1, ans);

            }
            map.put(cards[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    //Q3
    public static int countDistinct(int[] nums, int k, int p) {
        return 0;
    }
}
