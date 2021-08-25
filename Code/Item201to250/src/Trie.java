public class Trie {
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    private Trie[] child;
    private boolean isEnd;


    /**
     * Initialize your data structure here.
     */
    public Trie() {
        child = new Trie[26];
        isEnd = false;//初始化没有单词
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new Trie();
            }
            node = node.child[index];
        }

        //单词加完才标记
        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        if (node.isEnd == true) {
            return true;
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.child[index] == null) {
                return false;
            }
            node = node.child[index];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("abcd");
      boolean param_2 = obj.search("word");
      boolean param_4 = obj.search("abcd");
      boolean param_3 = obj.startsWith("abc");
    }
}
