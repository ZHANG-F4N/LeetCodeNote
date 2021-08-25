import java.util.ArrayList;
import java.util.List;

public class a212findWords {
    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

//        char[][] board = {
//                {'a'}
//        };
//        String[] words = {"a"};
        findWords(board, words);


    }
    //Trie字典树解决


    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    public static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                int index = ch - 'a';
                if (root.child[index] == null) {
                    root.child[index] = new TrieNode();
                }
                root = root.child[index];
            }
            root.word = words[i];
        }

        return root;
    }

    //DFS 可以解决，但超时
    public static List<String> findWords(char[][] board, String[] words) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (search(board, words[i])) {
                list.add(words[i]);
            }
        }
        return list;
    }

    public static boolean search(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (DFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean DFS(char[][] board, String word, int i, int j, int index) {

        boolean ans = false;

        if (board[i][j] == word.charAt(index)) {
            index++;
            if (index >= word.length()) {
                return true;
            }
            int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            int m = board.length;
            int n = board[0].length;

            for (int k = 0; k < dir.length; k++) {
                int dx = i + dir[k][0];
                int dy = j + dir[k][1];
                if (dx < 0 || dy < 0 || dx >= m || dy >= n || board[dx][dy] == '#') {
                    continue;
                }
                char temp = board[i][j];
                board[i][j] = '#';
                ans = ans || DFS(board, word, dx, dy, index);
                board[i][j] = temp;
            }
        }
        return ans;
    }
}
