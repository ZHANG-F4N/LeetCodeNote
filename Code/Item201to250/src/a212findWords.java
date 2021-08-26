import java.util.ArrayList;
import java.util.List;

public class a212findWords {
    public static void main(String[] args) {
//        char[][] board = {
//                {'o', 'a', 'a', 'n'},
//                {'e', 't', 'a', 'e'},
//                {'i', 'h', 'k', 'r'},
//                {'i', 'f', 'l', 'v'}
//        };
//        String[] words = {"oath", "pea", "eat", "rain"};

        char[][] board = {
                {'a'}
        };
        String[] words = {"a"};
        findWords2Trie(board, words);


    }

    //Trie字典树解决
    //DFS 可以解决，但超时
    public static List<String> findWords2Trie(char[][] board, String[] words) {
        ArrayList<String> list = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                DFSbyTrie(board, i, j, root, list);
            }
        }
        return list;
    }

    public static void DFSbyTrie(char[][] board, int i, int j, TrieNode root, ArrayList<String> ans) {
        char ch = board[i][j];
        if (ch == '#' || root.child[ch - 'a'] == null) return;

        root = root.child[ch - 'a'];

        if (root.word != null) {
            ans.add(root.word);
            root.word = null;
        }


        board[i][j] = '#';

        if (i > 0) DFSbyTrie(board, i - 1, j, root, ans);
        if (j > 0) DFSbyTrie(board, i, j - 1, root, ans);
        if (i < board.length - 1) DFSbyTrie(board, i + 1, j, root, ans);
        if (j < board[0].length - 1) DFSbyTrie(board, i, j + 1, root, ans);

        board[i][j] = ch;
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }


    public static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            TrieNode temp = root;
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = ch - 'a';
                if (temp.child[index] == null) {
                    temp.child[index] = new TrieNode();
                }
                temp = temp.child[index];
            }
            temp.word = words[i];
        }
        return root;
    }

//    //DFS 可以解决，但超时
//    public static List<String> findWords(char[][] board, String[] words) {
//        ArrayList<String> list = new ArrayList<>();
//
//        for (int i = 0; i < words.length; i++) {
//            if (search(board, words[i])) {
//                list.add(words[i]);
//            }
//        }
//        return list;
//    }
//
//    public static boolean search(char[][] board, String word) {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (DFS(board, word, i, j, 0)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean DFS(char[][] board, String word, int i, int j, int index) {
//
//        boolean ans = false;
//
//        if (board[i][j] == word.charAt(index)) {
//            index++;
//            if (index >= word.length()) {
//                return true;
//            }
//            int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
//            int m = board.length;
//            int n = board[0].length;
//
//            for (int k = 0; k < dir.length; k++) {
//                int dx = i + dir[k][0];
//                int dy = j + dir[k][1];
//                if (dx < 0 || dy < 0 || dx >= m || dy >= n || board[dx][dy] == '#') {
//                    continue;
//                }
//                char temp = board[i][j];
//                board[i][j] = '#';
//                ans = ans || DFS(board, word, dx, dy, index);
//                board[i][j] = temp;
//            }
//        }
//        return ans;
//    }
}
