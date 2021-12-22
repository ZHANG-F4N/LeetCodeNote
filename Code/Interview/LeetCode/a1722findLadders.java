import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a1722findLadders {
    public static void main(String[] args) {
        String[] strings = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(findLadders("hit", "cog", Arrays.asList(strings)));
    }

    public static List<String> findLadders(String beginWord, String endWord, List<String> wordList) {

        return null;
    }

    public static void DFS() {

    }


    class Trie {
        Trie[] child;
        String word;

        public Trie() {
            child = new Trie[26];
            word = null;
        }

        public void insert(String str) {
            Trie node = this;
            for (char c : str.toCharArray()) {
                int idx = c - 'a';
                if (node.child[idx] == null) node.child[idx] = new Trie();
                node = node.child[idx];
            }
            node.word = str;
        }

        // 可以改一个字符
//        public List<String> find(String str) {
//            Trie node = this;
//            int n = str.length();
//            for (int i = 0; i < n; i++) {
//
//            }
//
//
//        }
    }
}
