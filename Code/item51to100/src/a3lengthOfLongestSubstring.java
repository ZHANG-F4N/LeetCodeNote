import java.util.HashMap;

public class a3lengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int ans = 1;
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int[] dp = new int[s.length()];
        dp[0] = 1;
        hashMap.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!hashMap.containsKey(ch)) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = Math.min(i - hashMap.get(ch), dp[i - 1] + 1);
            }
            hashMap.put(ch, i);
            if (ans < dp[i]) {
                ans = dp[i];
            }
        }
        return ans;
    }
}
