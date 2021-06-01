import java.util.HashMap;
import java.util.Map;

public class a36isValidSudoku {
    public static void main(String[] args) {
        char[][] board =
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        HashMap<Character, Integer>[] rowHashMap = new HashMap[9];
        HashMap<Character, Integer>[] colHashMap = new HashMap[9];
        HashMap<Character, Integer>[] boxHashMap = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            colHashMap[i] = new HashMap<>();
            rowHashMap[i] = new HashMap<>();
            boxHashMap[i] = new HashMap<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char temp = board[i][j];
                if (temp != '.') {
                    rowHashMap[i].put(temp, rowHashMap[i].getOrDefault(temp, 0) + 1);
                    colHashMap[j].put(temp, colHashMap[j].getOrDefault(temp, 0) + 1);
                    boxHashMap[(i / 3) * 3 + j / 3].put(temp, boxHashMap[(i / 3) * 3 + j / 3].getOrDefault(temp, 0) + 1);
                    if (rowHashMap[i].get(temp) > 1 || colHashMap[j].get(temp) > 1 || boxHashMap[(i / 3) * 3 + j / 3].get(temp) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
