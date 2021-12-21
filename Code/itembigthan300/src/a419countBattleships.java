public class a419countBattleships {
    public static void main(String[] args) {
        System.out.println(countBattleships(new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        }));
    }

    public static int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if ((i - 1 < 0 || board[i - 1][j] != 'X') && (j - 1 < 0 || board[i][j - 1] != 'X'))
                        ans++;
                }
            }
        }
        return ans;
    }

}
