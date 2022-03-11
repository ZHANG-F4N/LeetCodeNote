public class a1423maxScore {
    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
    }


    /*
     *
     *  k = 3
     *  1   2   3   4   5   6   1
     *
     * */
    public static int maxScore(int[] cardPoints, int k) {


        int n = cardPoints.length;
        int sum = 0;
        for (int i = k - 1; i >= 0; i--) {
            sum += cardPoints[i];
        }
        int ans = sum;

        for (int lEnd = k - 2; lEnd >= 0; lEnd--) {
            sum -= cardPoints[lEnd + 1];
            sum += cardPoints[n - (k - lEnd - 1)];
            if (sum > ans) ans = sum;
        }
        sum -= cardPoints[0];
        sum += cardPoints[n - k];
        if (sum > ans) ans = sum;
        return ans;
    }
}
