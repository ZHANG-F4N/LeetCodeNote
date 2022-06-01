import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class contest294 {
    public static void main(String[] args) {

//        System.out.println(percentageLetter("sgawtb",'s'));
//        System.out.println(percentageLetter("foobar",'o'));
//        System.out.println(percentageLetter(
//                "vmvvvvvzrvvpvdvvvvyfvdvvvvpkvvbvvkvvfkvvvkvbvvnvvomvzvvvdvvvkvvvvvvvvvlvcvilaqvvhoevvlmvhvkvtgwfvvzy", 'v'));
        System.out.println(minimumLines(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 5}, {5, 7}, {6, 6}, {7, 5}, {8
                , 4}, {9, 4}, {10, 4}}));
    }

    //Q1
    public static int percentageLetter(String s, char letter) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        if (!hashMap.containsKey(letter))
            return 0;
        float ans = (((float) hashMap.get(letter)) / s.length() * 100) + 0.000001f;
        return (int) ans;
    }

    //q2
    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] val = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            val[i] = capacity[i] - rocks[i];
//            if (val[i] == 0) ans++;
        }
        Arrays.sort(val);
        int i = 0;
        while (i < n && additionalRocks != 0) {
            if (additionalRocks >= val[i]) {
                ans++;
                additionalRocks -= val[i];
            }
            i++;
        }

        return ans;
    }

    // Q3
    public static int minimumLines(int[][] stockPrices) {
        if (stockPrices.length == 1) return 0;
        int n = stockPrices.length;
        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));
        int ans = 1;
        int[] p1 = stockPrices[0];
        int[] p2 = stockPrices[1];
        for (int i = 2; i < n; i++) {
            int[] p3 = stockPrices[i];
            float dx1 = p2[0] - p1[0], dy1 = p2[1] - p1[1];
            float dx2 = p3[0] - p2[0], dy2 = p3[1] - p2[1];
            if (dx1 * dy2 == dx2 * dy1) continue;
            p1 = p2;
            p2 = p3;
            ans++;
        }
        return ans;
    }
}
