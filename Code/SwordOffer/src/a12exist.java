public class a12exist {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));

    }

    public static boolean exist(char[][] board, String word) {
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (DFS(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean DFS(char[][] board, String word, int index, int i, int j) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == word.charAt(index)) {
            char temp = board[i][j];
            board[i][j] = '#';
            boolean ans = DFS(board, word, index + 1, i + 1, j) || DFS(board, word, index + 1, i, j + 1) || DFS(board, word, index + 1, i - 1, j) || DFS(board, word, index + 1, i, j - 1);
            board[i][j] = temp;
            return ans;
        }
        return false;
    }
}
