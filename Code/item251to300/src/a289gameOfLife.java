public class a289gameOfLife {
    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board);
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public static void gameOfLife(int[][] board) {
        // 因为这道题目的输入是int[][]，而int是可以存储更多的比特位的。
        // 原有的最低位存储的是当前状态，那倒数第二低位存储下一个状态就行了。
        int row = board.length;
        int col = board[0].length;
        int[][] dxdy = {
                {-1, 0}, {1, 0}, {0, 1}, {0, -1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int alive = 0;
                for (int k = 0; k < dxdy.length; k++) {
                    int x = i + dxdy[k][0];
                    int y = j + dxdy[k][1];
                    if (x < 0 || x >= row || y < 0 || y >= col) {
                        continue;
                    }
                    //取最后一位
                    if ((board[x][y] & 1) == 1) {
                        alive++;
                    }
                }
                if ((board[i][j] & 1) == 1) {
                    if (alive < 2) {
                        board[i][j] = 0b01;
                    } else if (alive > 3) {
                        board[i][j] = 0b01;
                    } else {
                        board[i][j] = 0b11;
                    }
                } else {
                    if (alive == 3) {
                        board[i][j] = 0b10;
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
