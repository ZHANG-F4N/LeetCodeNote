import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class a438findAnagrams {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc").toString());
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        int[] strS = new int[26];
        int[] strP = new int[26];
        for (char c : p.toCharArray()) {
            strP[c - 'a']++;
        }
        char[] str = s.toCharArray();
        int left = 0;
        for (int i = 0; i < str.length; i++) {
            int idx = str[i] - 'a';
            strS[idx]++;
            // 当遍历到 字母 数量大于需要的数量时
            // 左指针就移动到将这个数减少到等于需要的数量的地方
            while (strS[idx] > strP[idx]) {
                int x = str[left] - 'a';
                strS[x]--;
                left++;
            }
            if (i - left + 1 == p.length()) {
                ans.add(left);
            }

        }
        return ans;
    }
}
