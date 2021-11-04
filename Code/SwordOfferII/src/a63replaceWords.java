import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a63replaceWords {
    public static void main(String[] args) {
        System.out.println(replaceWords(new ArrayList<>(Arrays.asList("bat", "rat")), "b"));
        System.out.println(replaceWords(new ArrayList<>(Arrays.asList("cat","bat","rat")), "the cattle was rattled by the battery"));
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        trie root = new trie();
        Iterator<String> it = dictionary.iterator();
        while (it.hasNext()) {
            root.insert(it.next());
        }

        String[] arr = sentence.trim().split("\\s+");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            ans.append(root.preSearch(arr[i]) + " ");
        }

        return ans.toString().trim();
    }

    static class trie {
        trie[] child;
        String isWord;

        public trie() {
            child = new trie[26];
            isWord = null;
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            trie temp = this;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new trie();
                }
                temp = temp.child[ch - 'a'];
            }
            temp.isWord = word;
        }

        public String preSearch(String word) {
            char[] chars = word.toCharArray();
            trie temp = this;
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                if (temp.child[ch - 'a'] == null) {
                    return word;
                }
                temp = temp.child[ch - 'a'];
                if (!(temp.isWord == null)) {
                    return temp.isWord;
                }
            }
            return word;
        }
    }
}
