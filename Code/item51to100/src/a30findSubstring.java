import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a30findSubstring {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo",
                "the"}).toString());
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> ans = new ArrayList<>();

        int wordNum = words.length;
        if (wordNum == 0) {
            return ans;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int win = wordLen * wordNum;

        //遍历字符串s
        for (int i = 0; i <= s.length() - win; i++) {
            //若以i开头的子串不存在于哈希表中，也就无需后续比较，直接continue
            if (!wordMap.containsKey(s.substring(i, i + wordLen))) continue;
            //若存在：
            //由于wordMap和i后续还要用到，不能对其进行更改，故定义两个临时变量：哈希表tmp、整型j
            HashMap<String, Integer> tempHashMap = new HashMap<>(wordMap);
            int j = i;
            //对每一个“窗口”进行判断，若该窗口子串存在且其个数大于0，将其个数减1，窗口向后移动一个步长，否则退出循环
            for (int count = words.length; count > 0; count--) {
                String a = s.substring(j, j + wordLen);
                if (!tempHashMap.containsKey(a) || tempHashMap.get(a) == 0) break;
                else {
                    j += wordLen;
                    tempHashMap.put(a, tempHashMap.get(a) - 1);
                }
                //若直到进行了 words.length 个窗口的判断子串都存在，匹配成功，将 i 的值放入ans
                if (j == i + wordLen * words.length) ans.add(i);
            }
        }
        return ans;

    }

}
