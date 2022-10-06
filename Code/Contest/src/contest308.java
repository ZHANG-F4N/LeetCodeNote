import java.util.Arrays;

public class contest308 {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(answerQueries(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21})));
//        System.out.println(removeStars("leet**cod*e"));
        System.out.println(garbageCollection(new String[]{"G", "P", "GP", "GG"}, new int[]{2, 4, 3}));
    }

    public static int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        int[] ans = new int[m];
        Arrays.sort(nums);
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < m; i++) {
            long total = sum;
            for (int j = n - 1; j >= 0; j--) {
                if (total <= queries[i]) {
                    ans[i] = j + 1;
                    break;
                } else {
                    total -= nums[j];
                }
            }
        }
        return ans;


    }

    public static String removeStars(String s) {

        // "leet**cod*e"
        int n = s.length();
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '*') {
                ans.deleteCharAt(ans.length() - 1);
            } else {
                ans.append(chars[i]);
            }
        }

        return ans.toString();
    }

    public static int garbageCollection(String[] garbage, int[] travel) {
        int ans = garbage[0].length();
        int p = 0, m = 0, g = 0;
        for (int i = 1; i < garbage.length; i++) {
            String gar = garbage[i];

            if (gar.contains("M")) {
                for (int j = m + 1; j <= i; j++) {
                    ans += travel[j - 1];
                }
                m = i;
            }
            if (gar.contains("P")) {
                for (int j = p + 1; j <= i; j++) {
                    ans += travel[j - 1];
                }
                p = i;
            }
            if (gar.contains("G")) {
                for (int j = g + 1; j <= i; j++) {
                    ans += travel[j - 1];
                }
                g = i;
            }
            ans += gar.length();
        }
        return ans;
    }

    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

    }
}
