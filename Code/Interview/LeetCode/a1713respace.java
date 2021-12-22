public class a1713respace {
    public static void main(String[] args) {
        System.out.println(respace(new String[]{"looked", "just", "like", "her", "brother"},
                "jesslookedjustliketimherbrother"));
    }

    public static int respace(String[] dictionary, String sentence) {
        Trie root = new Trie();
        for (String s : dictionary) {
            root.build(s);
        }
        return root.find(sentence);
    }

    static class Trie {
        Trie child[];
        String word;

        public Trie() {
            child = new Trie[26];
            word = null;
        }

        public void build(String str) {
            Trie node = this;
            for (char c : str.toCharArray()) {
                int idx = c - 'a';
                if (node.child[idx] == null) node.child[idx] = new Trie();
                node = node.child[idx];
            }
            node.word = str;
        }

        public int find(String str) {
            int ans = 0;
            char[] cStr = str.toCharArray();
            Trie node = this;




            int j = 0;
            for (int i = 0; i < cStr.length; i++) {
                int idx = cStr[i] - 'a';
                if (node.child[idx] == null) {
                    ans += i - j;
                    node = this;
                    continue;
                } else if (node.word != null) {
                    j = i;
                    node = this;
                    continue;
                }
                node = node.child[idx];
            }

            return ans;
        }
    }
}
