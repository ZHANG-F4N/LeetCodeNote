import java.awt.image.AreaAveragingScaleFilter;
import java.security.spec.DSAGenParameterSpec;
import java.util.Arrays;
import java.util.Comparator;

public class LeetCode0911a2 {
    public static void main(String[] args) {
        int[] cards = {1, 2, 8, 9};
        int cnt = 3;
        System.out.println(maxmiumScore2(cards, cnt));
    }


    public static int maxmiumScore2(int[] cards, int cnt) {
        int ans = 0;
        Arrays.sort(cards);
        int index = cards.length - 1;
        int odd = Integer.MAX_VALUE;
        int Noodd = Integer.MAX_VALUE;

        for (int i = index; i > index - cnt + 1; i--) {
            if (cards[i] % 2 == 0) {
                Noodd = cards[i];
            } else {
                odd = cards[i];
            }
            ans += cards[i];
        }
        //System.out.println(ans);

        int res = ans % 2 == 0 ? ans : 0;

        index = cards.length - cnt + 1;
        while (index >= 0 && ans % 2 != 0) {
            if (cards[index] % 2 == 0 && odd != Integer.MAX_VALUE)
                res = Math.max(res, ans - odd + cards[index]);
            else if (Noodd != Integer.MAX_VALUE)
                res = Math.max(res, ans - Noodd + cards[index]);
            index--;
        }
        return res % 2 == 0 ? res : 0;
    }
}
