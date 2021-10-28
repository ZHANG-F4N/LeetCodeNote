import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a15findAnagrams {
    public static void main(String[] args) {
        findAnagrams("baa", "aa");
        findAnagrams("cbaebabacd", "abc");
        findAnagrams("abab", "ab");
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int N = s.length();
        int M = p.length();

        ArrayList<Integer> list = new ArrayList<>();
        if (M > N) {
            return list;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for (char ch : p.toCharArray()) {
            pCnt[ch - 'a']++;
        }
        int left = 0;
        for (int i = 0; i < N; i++) {
            int x = s.charAt(i) - 'a';
            sCnt[x]++;
            while (sCnt[x] > pCnt[x]) {
                int l = s.charAt(left) - 'a';
                sCnt[l]--;
                left++;
            }
            if(i - left + 1 == M){
                list.add(left);
            }

        }
        return list;


    }
}
