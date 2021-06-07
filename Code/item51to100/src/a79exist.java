public class a79exist {
    public static void main(String[] args) {
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        String word = "ABCCED";
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        String word = "ABCB";
        char[][] board = {
                {'A', 'B'}
        };
        String word = "BA";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        int[][] visited = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0) && DFS(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //DFS
    public static boolean DFS(char[][] board, int[][] visited, String word, int i, int j, int k) {
        visited[i][j] = 1;
        if (board[i][j] != word.charAt(k)) {
            visited[i][j] = 0;
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        if (i - 1 >= 0 && visited[i - 1][j] == 0 && DFS(board, visited, word, i - 1, j, k + 1)) {
            return true;
        }
        if (i + 1 < board.length && visited[i + 1][j] == 0 && DFS(board, visited, word, i + 1, j, k + 1)) {
            return true;
        }
        if (j - 1 >= 0 && visited[i][j - 1] == 0 && DFS(board, visited, word, i, j - 1, k + 1)) {
            return true;
        }
        if (j + 1 < board[0].length && visited[i][j + 1] == 0 && DFS(board, visited, word, i, j + 1, k + 1)) {
            return true;
        }
        visited[i][j] = 0;
        return false;
    }
}
