import javax.security.auth.login.CredentialNotFoundException;

public class a32isAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] flag = new int[26];
        for (char ch : s.toCharArray()) {
            flag[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            flag[ch - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (flag[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
