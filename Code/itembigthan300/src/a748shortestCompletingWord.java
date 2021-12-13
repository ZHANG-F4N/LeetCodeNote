import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.concurrent.ConcurrentHashMap;

public class a748shortestCompletingWord {
    public static void main(String[] args) {
        System.out.println(shortestCompletingWord("1s3 456", new String[]{"looks", "pest", "stew", "show"}));
        System.out.println(shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
    }

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        int[] cnt = new int[26];
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                cnt[c - 'a']++;
            }
        }

        int min = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            int[] tempCnt = new int[26];
            for (char c : temp.toCharArray()) {
                tempCnt[c - 'a']++;
            }
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                if (tempCnt[j] < cnt[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag && min > temp.length()) {
                min = temp.length();
                idx = i;
            }
        }
        return words[idx];

    }
}
