import java.util.ArrayList;
import java.util.Arrays;

public class a1611divingBoard {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(divingBoard(1, 2, 3)));
    }

    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        if (shorter == longer) return new int[]{longer*k};
        int[] ans = new int[k+1];
        for (int i = 0; i <= k; i++) {
            int len = shorter * (k - i) + longer * i;
            ans[i] = len;
        }
        return ans;
    }
}
