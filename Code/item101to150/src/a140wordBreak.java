import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a140wordBreak {
    public static void main(String[] args) {
        String s = "catsanddog";
        ArrayList<String> wordDict = new ArrayList<>();


        String[] words = {"cat", "cats", "and", "sand", "dog"};
        for (int i = 0; i < words.length; i++) {
            wordDict.add(words[i]);
        }
        wordBreak(s, wordDict);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String> ans = new ArrayList<>();



        return ans;
    }

    public static Trie buildTrie(List<String> wordDict) {
        Trie root = new Trie();

        Iterator<String> it = wordDict.iterator();

        while (it.hasNext()) {
            String str = it.next();
            Trie temp = root;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new Trie();
                }
                temp = temp.child[ch - 'a'];
            }
            temp.word = str;
        }

        return root;
    }

    static class Trie {
        Trie[] child = new Trie[26];
        String word;
    }
}