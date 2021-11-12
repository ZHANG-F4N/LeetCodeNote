import java.util.HashSet;
import java.util.Iterator;

public class a488findMinStep {
    public static void main(String[] args) {
        System.out.println(findMinStep("G", "GGGG"));
        System.out.println(findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(findMinStep("WRRBBW", "RB"));
    }


    public static HashSet<Integer> ans;

    public static int findMinStep(String board, String hand) {
        ans = new HashSet<>();
        for (int i = 0; i < hand.length(); i++) {
            DFS(new StringBuilder(board), hand.toCharArray(), i, 0);
        }
        int min = Integer.MAX_VALUE;
        Iterator<Integer> it = ans.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            min = min > temp ? temp : min;
        }
        min = min == Integer.MAX_VALUE ? -1 : min;
        return min;
    }

    public static void DFS(StringBuilder board, char[] hand, int idx, int len) {
        if (board.length() == 0) {
            ans.add(len);
            return;
        }
        if (len == hand.length) {
            return;
        }
        char ch = hand[idx];
        int mid = board.indexOf(String.valueOf(ch), 0);
        board = new StringBuilder(board.substring(0, mid+1) +ch+ board.substring(mid + 1
                , board.length()));
        while (mid != -1) {
            int left = mid;
            int right = mid;
            while (left > 0 && board.charAt(left - 1) == ch) left--;
            while (right < board.length() - 1 && board.charAt(right + 1) == ch) right++;
            if (right - left + 1 >= 3) {
                board = new StringBuilder(board.substring(0, left) + board.substring(right + 1
                        , board.length()));
                if (left >= board.length()) {
                    break;
                }
                mid = board.indexOf(String.valueOf(ch), 0);
            } else {
                mid = board.indexOf(String.valueOf(ch), mid + 1);
            }
        }
        if (board.length() == 0) {
            ans.add(len);
            return;
        }
        if (len + 1 == hand.length) {
            return;
        }
        char temp = hand[idx];
        hand[idx] = '#';
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != '#')
                DFS(board, hand, i, len + 1);
        }
        hand[idx] = temp;
    }
}
