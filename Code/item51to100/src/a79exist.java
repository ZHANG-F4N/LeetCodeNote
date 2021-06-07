public class a79exist {
    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        int[][] visited = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean flag = DFS(board, visited, word, i, j, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    //DFS
    public static boolean DFS(char[][] board, int[][] visited, String word, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        if (i - 1 >= 0 && visited[i - 1][j] == 0 && board[i - 1][j] == word.charAt(k)) {
            visited[i - 1][j] = 1;
            DFS(board, visited, word, i - 1, j, k + 1);
        }
        if (i + 1 < board.length && visited[i + 1][j] == 0 && board[i + 1][j] == word.charAt(k)) {
            visited[i + 1][j] = 1;
            DFS(board, visited, word, i + 1, j, k + 1);
        }
        if (j - 1 >= 0 && visited[i][j - 1] == 0 && board[i][j - 1] == word.charAt(k)) {
            visited[i][j - 1] = 1;
            DFS(board, visited, word, i, j - 1, k + 1);
        }
        if (j + 1 >= 0 && visited[i][j + 1] == 0 && board[i][j + 1] == word.charAt(k)) {
            visited[i][j + 1] = 1;
            DFS(board, visited, word, i, j + 1, k + 1);
        }
        return false;
    }
}
