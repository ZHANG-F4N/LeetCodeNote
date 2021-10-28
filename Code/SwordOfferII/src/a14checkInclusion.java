import java.util.HashMap;

public class a14checkInclusion {
    public static void main(String[] args) {

        System.out.println(checkInclusion2("abc", "ccccbbbbaaaa"));
        System.out.println(checkInclusion("adc", "dcda"));
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));

    }

    public static boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }
        int N = s1.length();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashMap<Character, Integer> tempMap = new HashMap<>();
        for (char ch : s1.toCharArray()) {
            hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
        }

        int validNum = 0;
        char[] arr = s2.toCharArray();
        for (int i = 0; i < N; i++) {
            char ch = arr[i];
            if (!hashMap.containsKey(ch)) {
                continue;
            }
            tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
            if (hashMap.get(ch) >= tempMap.get(ch)){
                validNum++;
            }
        }
        if (validNum == N) {
            return true;
        }

        for (int i = N; i < s2.length(); i++) {
            char ch = arr[i];
            if (hashMap.containsKey(ch)){
                tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
                if (tempMap.get(ch) <= hashMap.get(ch)){
                    validNum++;
                }
            }
            ch = arr[i - N];
            if (hashMap.containsKey(ch) ) {
                if (tempMap.get(ch) <= hashMap.get(ch)){
                    validNum--;
                }
                if (tempMap.get(ch) == 1) {
                    tempMap.remove(ch);
                } else {
                    tempMap.put(ch, tempMap.get(ch) - 1);
                }
            }
            if (validNum == N) {
                return true;
            }

        }
        return false;
    }
    public static boolean checkInclusion2(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }



}
