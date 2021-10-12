import java.util.Arrays;
import java.util.stream.IntStream;

public class a135candy {
    public static void main(String[] args) {
        int[] ratings = {1, 3, 4, 5, 2};
//        int[] ratings = {1, 0, 2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        int N = ratings.length;
        if (N == 1) {
            return 1;
        }
        int[] candies = new int[N];
        candies[0] = 1;

        for (int i = 1; i < N; i++) {
            candies[i] = 1;
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int ans = candies[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
            ans += candies[i];
        }
        //ans = IntStream.of(candies).sum();
        return ans;
    }
}
