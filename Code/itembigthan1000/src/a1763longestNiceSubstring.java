import java.util.HashMap;

public class a1763longestNiceSubstring {
    public static void main(String[] args) {
        //System.out.println((char) ('A' + 32));
        System.out.println(longestNiceSubstring("dDzeE"));
        System.out.println(longestNiceSubstring("YazaAay"));
    }

    public static String longestNiceSubstring(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int len = 0;
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            for (int j = i; j < n; j++) {
                char ch = chArr[j];
                hashMap.put(ch, hashMap.getOrDefault(ch, 0) + 1);
                boolean flag = true;
                for (int k = i; k <= j; k++) {
                    char c = chArr[k];
                    char tc;
                    if (c <= 'Z') {
                        tc = Character.toLowerCase(c);
                    } else {
                        tc = Character.toUpperCase(c);
                    }
                    if (!hashMap.containsKey(tc)) {
                        flag = false;
                        break;
                    }
                }
                if (flag && len < (j - i)) {
                    len = j - i;
                    l = i;
                    r = j;
                }
            }
        }
        if (len == 0) return "";
        return s.substring(l, r + 1);
    }
}
