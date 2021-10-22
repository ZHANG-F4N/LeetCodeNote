public class a211WordDictionary {
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("a");
        obj.addWord("ab");
        boolean param_2 = obj.search("a");
        boolean param_3 = obj.search("a.");
        boolean param_4 = obj.search("ab");
        boolean param_5 = obj.search(".a");
        boolean param_6 = obj.search(".b");
        boolean param_7 = obj.search("ab.");
        boolean param_8 = obj.search(".");
        boolean param_9 = obj.search("..");
    }

}

class WordDictionary {
    class Node {
        Node[] leaf;
        Boolean isWord;

        public Node() {
            leaf = new Node[26];
            isWord = false;
        }
    }

    Node wordTree;

    public WordDictionary() {
        this.wordTree = new Node();
    }

    public void addWord(String word) {
        Node index = this.wordTree;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (index.leaf[ch - 'a'] == null){
                index.leaf[ch - 'a'] = new Node();
            }
            index = index.leaf[ch - 'a'];
        }
        index.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, this.wordTree);
    }

    public boolean dfs(String str, int index, Node tree) {
        if (index == str.length()) {
            return tree.isWord;
        }
        char ch = str.charAt(index);
        if (Character.isLetter(ch)) {
            if (tree.leaf[ch - 'a'] == null) {
                return false;
            }
            return dfs(str, index + 1, tree.leaf[ch - 'a']);
        } else {
            for (int i = 0; i < 26; i++) {
                Node temp = tree.leaf[i];
                if (temp != null && dfs(str, index + 1, temp)) {
                    return true;
                }
            }
            return false;
        }
    }
}
