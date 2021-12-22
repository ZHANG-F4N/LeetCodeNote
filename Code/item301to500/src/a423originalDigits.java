import java.util.*;

public class a423originalDigits {
    public static void main(String[] args) {
        System.out.println(originalDigits("zerozero"));
        System.out.println(originalDigits("owoztneoer"));
        System.out.println(originalDigits("fviefuro"));
    }

    public static String originalDigits(String s) {
        String[] digit = {"zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};

        HashMap<Integer, HashMap> map = new HashMap<>();
        for (int i = 0; i < digit.length; i++) {
            HashMap<Character, Integer> temp = new HashMap<>();
            for (char c : digit[i].toCharArray()) {
                temp.put(c, temp.getOrDefault(c, 0) + 1);
            }
            map.put(i, temp);
        }
        int[] orderNum = {0, 2, 4, 6, 8, 1, 3, 7, 5, 9};
        char[] order = {'z', 'w', 'u', 'x', 'g', 'o', 'h', 's', 'f', 'i'};
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int[] ans = new int[10];
        for (int i = 0; i < order.length; i++) {
            int num = count[order[i] - 'a'];
            if (num == 0) {
                continue;
            }
            for (int j = 0; j < num; j++) {
                ans[orderNum[i]]++;
            }
            HashMap<Character, Integer> temp = map.get(orderNum[i]);
            for (Map.Entry<Character, Integer> en : temp.entrySet()) {
                count[en.getKey() - 'a'] -= en.getValue() * num;
            }
        }
        // String + 操作相比 append 很慢
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < ans[i]; j++) {
                temp.append(i);
            }
        }
        return temp.toString();
    }
}
