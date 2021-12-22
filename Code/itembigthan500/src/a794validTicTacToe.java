public class a794validTicTacToe {
    public static void main(String[] args) {
        System.out.println(validTicTacToe(new String[]{"O  ", "   ", "   "}));
    }

    public static boolean validTicTacToe(String[] board) {
        char[][] map = new char[3][3];

        for (int i = 0; i < 3; i++) {
            map[i] = board[i].toCharArray();
        }
        int numX = 0;
        int numO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'X') {
                    numX++;
                } else if (map[i][j] == 'O') {
                    numO++;
                }
            }
        }

        if (numX - numO > 1 || numO - numX >= 1) {
            return false;
        }
        if (judge(map, 'X') && numX - numO != 1) {
            return false;
        }
        if (judge(map, 'O') && numX - numO != 0) {
            return false;
        }
        return true;

    }

    public static boolean judge(char[][] board, char pin) {
        for (int i = 0; i < 3; i++) {
            int colN = 0;
            int rowN = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == pin) {
                    colN++;
                }
                if (board[j][i] == pin) {
                    rowN++;
                }
            }
            if (colN == 3 || rowN == 3) {
                return true;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == pin) {
            return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == pin) {
            return true;
        }
        return false;
    }
}
