public class a1604tictactoe {
    public static void main(String[] args) {
        System.out.println(tictactoe(new String[]{"O X"," XO","X O"}));
        System.out.println(tictactoe(new String[]{"OOX","XXO","OX "}));
        System.out.println(tictactoe(new String[]{"OOX", "XXO", "OXO"}));
    }

    public static String tictactoe(String[] board) {
        int n = board.length;
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = board[i].toCharArray();
        }

        boolean xDg = true;
        boolean xUDg = true;
        boolean oDg = true;
        boolean oUDg = true;
        boolean voidP = false;
        for (int i = 0; i < n; i++) {
            boolean xRow = true;
            boolean oRow = true;
            boolean xCol = true;
            boolean oCol = true;
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 'X') xRow = false;
                if (map[i][j] != 'O') oRow = false;
                if (map[j][i] != 'O') oCol = false;
                if (map[j][i] != 'X') xCol = false;
                if (map[j][i] == ' ') voidP = true;
            }
            if (map[i][i] != 'X') xDg = false;
            if (map[i][i] != 'O') oDg = false;
            if (map[i][n - 1 - i] != 'X') xUDg = false;
            if (map[i][n - 1 - i] != 'O') oUDg = false;
            if (oCol || oRow) return "O";
            if (xCol || xRow) return "X";
        }
        if (xDg || xUDg) return "X";
        if (oDg || oUDg) return "O";
        if (voidP) return "Pending";
        return "Draw";
    }
}
