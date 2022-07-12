public class a522findLUSlength {
    public static void main(String[] args) {
        System.out.println(findLUSlength(new String[]{"aba", "cdc", "eae"}));
    }

    public static int findLUSlength(String[] strs) {

        int ans = -1;
        for (int i = 0; i < strs.length; i++) {
            String tar = strs[i];
            boolean flag = true;
            for (int j = 0; j < strs.length; j++) {
                String val = strs[j];
                // 短的是长的子序列
                if (i != j && isSubseq(tar, val)) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans = Math.max(tar.length(), ans);
        }
        return ans;

    }

    public static boolean isSubseq(String s, String t) {
        int ptS = 0, ptT = 0;
        while (ptS < s.length() && ptT < t.length()) {
            if (s.charAt(ptS) == t.charAt(ptT)) {
                ++ptS;
            }
            ++ptT;
        }
        return ptS == s.length();
    }

}
