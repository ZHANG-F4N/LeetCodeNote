public class a130solve {
    public static void main(String[] args) {
//        char[][] board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
//        };
//        char[][] board = {
//                {'O'}
//        };
        char[][] board = {
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'X'}
        };


        solve(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + "\t");
            }
            System.out.println();
        }

    }

    public static void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                DFS(board, i, 0);
            }
            if (board[i][m - 1] == 'O') {
                DFS(board, i, m - 1);
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') {
                DFS(board, 0, i);
            }
            if (board[n - 1][i] == 'O') {
                DFS(board, n - 1, i);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static void DFS(char[][] board, int i, int j) {
        int n = board.length;
        int m = board[0].length;
        int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        if (i < 0 || i >= n || j < 0 || j >= m) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = 'A';
        } else {
            return;
        }
        for (int k = 0; k < direct.length; k++) {
            DFS(board, i + direct[k][0], j + direct[k][1]);
        }
    }
}
