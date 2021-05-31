public class a242isAnagram {
    public static void main(String[] args) {
        String s = "rat", t = "car";
        System.out.println(isAnagram(s, t));

    }

    public static boolean isAnagram(String s, String t) {
        int flag[] = new int[26];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            flag[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (flag[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
