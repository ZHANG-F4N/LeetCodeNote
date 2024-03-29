import java.awt.image.AreaAveragingScaleFilter;
import java.security.spec.DSAGenParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LeetCode0911a2 {
    public static void main(String[] args) {
        int[] cards = {2,3};
        int cnt = 1;
        System.out.println(maxmiumScore2(cards, cnt));
                                                                                                  }


    public static int maxmiumScore2(int[] cards, int cnt) {
        Arrays.sort(cards);

        int ans = 0;

        int oddIndex = Integer.MAX_VALUE;
        int evenIndex = Integer.MAX_VALUE;
        for (int i = cards.length - 1; i >= cards.length - cnt; i--) {
            if (cards[i] % 2 == 0) {
                evenIndex = i;
            } else {
                oddIndex = i;
            }
            ans += cards[i];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        int maxOdd = Integer.MAX_VALUE;
        int maxEven = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length - cnt; i++) {
            if (cards[i] % 2 == 0) {
                maxEven = i;
            } else {
                maxOdd = i;
            }
        }
//        System.out.println(oddIndex);
//        System.out.println(evenIndex);
//        System.out.println(maxOdd);
//        System.out.println(maxEven);
        int temp1 = 0;
        int temp2 = 0;
        if (maxEven != Integer.MAX_VALUE && oddIndex != Integer.MAX_VALUE) {
            temp1 = ans - cards[oddIndex] + cards[maxEven];
        }
        if (maxOdd != Integer.MAX_VALUE && evenIndex != Integer.MAX_VALUE) {
            temp2 = ans - cards[evenIndex] + cards[maxOdd];
        }

        ans = Math.max(temp1, temp2);
        ans = Math.max(0,ans);


        return ans;


    }
}
