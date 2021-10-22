import java.util.HashMap;

public class a76minWindow {
    public static void main(String[] args) {
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        int N = s.length();
        int M = t.length();

        if (M == 0 || N == 0 || N < M) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // 有个优化的办法是使用数组,比HashMap快一点的
        HashMap<Character, Integer> winHashMap = new HashMap<>();
        HashMap<Character, Integer> tHashMap = new HashMap<>();
        for (int k = 0; k < M; k++) {
            char ch = t.charAt(k);
            tHashMap.put(ch, tHashMap.getOrDefault(ch, 0) + 1);
        }

        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        int distance = 0;

        while (j < N) {
            if (distance < M) {
                char ch = s.charAt(j);
                if (winHashMap.getOrDefault(ch, 0) < tHashMap.getOrDefault(ch, 0)) {
                    distance++;
                }
                winHashMap.put(ch, winHashMap.getOrDefault(ch, 0) + 1);
                j++;
            }
            while (distance == M) {
                char ch = s.charAt(i);
                if (winHashMap.getOrDefault(ch, 0) <= tHashMap.getOrDefault(ch, 0)) {
                    distance--;
                    if (j - i < min) {
                        min = j - i;
                        ans = new StringBuilder(s.substring(i, j));
                    }
                }
                i++;
                if (winHashMap.getOrDefault(ch, 0) > 1) {
                    winHashMap.replace(ch, winHashMap.get(ch) - 1);
                } else {
                    winHashMap.remove(ch);
                }

            }
        }

        return ans.toString();
    }
}
