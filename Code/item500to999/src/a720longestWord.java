import javax.print.DocFlavor;

public class a720longestWord {
    public static void main(String[] args) {
        System.out.println(longestWord(new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"}));
    }

    public static String longestWord(String[] words) {
        Trie root = Trie.insert(words);

        String ans = "";
        for (int i = 0; i < words.length; i++) {
            if (Trie.search(root, words[i])) {
                if (words[i].length() > ans.length() || (words[i].length() == ans.length() && words[i].compareTo(ans) < 0)) {
                    ans = words[i];
                }
            }
        }
        return ans;


    }

    static class Trie {
        private Trie[] child;
        private String val;

        public Trie() {
            child = new Trie[26];
            val = null;
        }

        public static Trie insert(String[] words) {
            Trie root = new Trie();

            for (String word : words) {
                Trie temp = root;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    int idx = chars[i] - 'a';
                    if (temp.child[idx] == null)
                        temp.child[idx] = new Trie();
                    temp = temp.child[idx];
                }
                temp.val = word;
            }
            return root;
        }

        public static boolean search(Trie root, String word) {
            char[] chars = word.toCharArray();
            Trie temp = root;
            for (int i = 0; i < chars.length; i++) {
                int idx = chars[i] - 'a';
                if (temp.child[idx] == null || temp.child[idx].val == null) {
                    return false;
                }
                temp = temp.child[idx];
            }
            return temp != null && temp.val != null;
        }


    }
}
