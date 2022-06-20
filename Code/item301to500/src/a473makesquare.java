import java.util.Arrays;
import java.util.Comparator;

public class a473makesquare {
    public static void main(String[] args) {
        System.out.println(makesquare(new int[]{3, 3, 3, 3, 4}));
    }

    public static boolean ans;

    public static boolean makesquare(int[] matchsticks) {
        long sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;
        long len = sum >> 2;
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < matchsticks.length; i++) {
//            list.add(matchsticks[i]);
//        }
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }

        int[] edges = new int[4];
        return backtrace(matchsticks, 0, edges, len);
    }

    public static boolean backtrace(int[] matchsticks, int idx, int[] edges, long len) {
        if (idx == matchsticks.length) return true;

        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[idx];
            if (edges[i] <= len && backtrace(matchsticks, idx + 1, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[idx];
        }
        return false;
    }
}
