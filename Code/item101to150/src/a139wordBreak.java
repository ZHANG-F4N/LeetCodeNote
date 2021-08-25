import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class a139wordBreak {
    public static void main(String[] args) {
        String s = "applepenapple";
        ArrayList<String> wordDict = new ArrayList<>();
        wordDict.add("apple");
        wordDict.add("pen");
        System.out.println(wordBreak(s, wordDict));
    }

    /*
     *   这一题的动态规划的思路和前面将字符串划分为回文串的动态规划思路类似，
     *   令dp[i]表示s[0~i]是否能够被划分为wordDict中的字符串的组合，则对于d[i]的取值有如下分析：
     *   1.s[0~i]本身就是wordDict中的字符串，那么dp[i] = true
     *   2.s[0~i]不是wordDict中的字符串，则需要向前遍历dp，看是否存在dp[j] = true同时s[j+1~i]为wordDict中的字符串
     *   最后需要返回dp[s.length()]
     * */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // 依次进行字符串分割，分割成s[0~i] n个子串
        // 对于每个子串，从位置j 将子串分成两个更小的子串s[0-j] s[j -i]，
        // 判断两个子串是否包含子给定的字符串集合中
        // 前一个子串只需要用dp[j]来判断
        // 令dp[i]表示s[0~i]是否能够被划分为wordDict中的字符串的组合
        HashSet<String> hashSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int MaxLen = 0;
        int MinLen = Integer.MAX_VALUE;//0x 7fff ffff
        Iterator<String> it = wordDict.iterator();
        while (it.hasNext()) {
            int length = it.next().length();
            if (length > MaxLen) {
                MaxLen = length;
            }
            if (length < MinLen) {
                MinLen = length;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (i - j  < MinLen) {
                    continue;
                }
                if (i - j > MaxLen) {
                    continue;
                }
                if (dp[j] && hashSet.contains(s.substring(j, i))) {//"smiles".substring(1, 5) returns "mile"
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
