import java.util.ArrayDeque;
import java.util.HashMap;

public class a773slidingPuzzle {
    public static void main(String[] args) {
        System.out.println(slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
        System.out.println(slidingPuzzle(new int[][]{{3, 2, 4}, {1, 5, 0}}));
    }


    public static int slidingPuzzle(int[][] board) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int num = 0;
        for (int i = 0; i < 6; i++) {
            num += board[i / 3][i % 3];
            num *= 10;
        }
        num /= 10;
        hashMap.put(num, 1);
        if (num == 123450) {
            return 0;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(num);
        int deep = 1;
        int levelNum = 1;
        while (!deque.isEmpty()) {
            int temp = deque.poll();
            levelNum--;
            if (temp == 123450) {
                return deep;
            }
            for (int i = 0; i < 6; i++) {
                int[] direct = {i + 3, i - 3, i - 1, i + 1};
                for (int j = 0; j < direct.length; j++) {
                    if (direct[j] < 6 && direct[j] >= 0) {
                        int save = temp / (10 * (5 - i));
                    }
                }
            }

        }
        return deep;


    }


}
