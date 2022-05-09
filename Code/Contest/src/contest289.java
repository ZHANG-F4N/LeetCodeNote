import java.util.HashMap;
import java.util.Map;

public class contest289 {
    public static void main(String[] args) {


        System.out.println(maxTrailingZeros(new int[][]{{23, 17, 15, 3, 20}, {8, 1, 20, 27, 11}, {9, 4, 6, 2,
                21}, {40, 9, 1, 10, 6}, {22, 7, 4, 5, 3}}));
//        System.out.println(digitSum("11111222223", 3));
//        System.out.println(minimumRounds(new int[]{2, 3, 3}));
//        System.out.println(minimumRounds(new int[]{5, 5, 5, 5}));
//
//        System.out.println(minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}));
    }

    // Q1
    public static String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder next = new StringBuilder();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                builder.append(s.charAt(i));
                if (builder.length() >= k || i == s.length() - 1) {
                    int sum = 0;
                    for (int j = 0; j < k && j < builder.length(); j++) {
                        sum += builder.charAt(j) - '0';
                    }
                    next.append(sum);
                    builder = new StringBuilder();
                }
            }
            s = next.toString();
        }
        return s;

    }

    public static int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int task : tasks) {
            hashMap.put(task, hashMap.getOrDefault(task, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (en.getValue() == 1) return -1;
            if (en.getValue() % 3 == 0) ans += en.getValue() / 3;
            else if (en.getValue() % 3 == 2) ans += en.getValue() / 3 + 1;
            else ans += en.getValue() / 3 + 1;
        }
        return ans;
    }


    public static int maxTrailingZeros(int[][] grid) {


        return 0;

    }
}
