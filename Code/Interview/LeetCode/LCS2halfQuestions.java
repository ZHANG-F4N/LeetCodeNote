import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LCS2halfQuestions {
    public static void main(String[] args) {
        System.out.println(halfQuestions(new int[]{2,1,6,2}));
        System.out.println(halfQuestions(new int[]{1, 5, 1, 3, 4, 5, 2, 5, 3, 3, 8, 6}));
    }

    public static int halfQuestions(int[] questions) {
        int[] ints = new int[1001];
        for (int question : questions) {
            ints[question]++;
        }
        Arrays.sort(ints);
        int ans = 0;
        int num = 0;
        for (int i = ints.length - 1; i >= 0; i--) {
            num += ints[i];
            ans++;
            if (num >= (questions.length >> 1)) {
                return ans;
            }
        }
        return ans;

    }
}
