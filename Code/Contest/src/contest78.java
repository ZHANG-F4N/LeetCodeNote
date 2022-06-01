import java.util.Arrays;

public class contest78 {
    public static void main(String[] args) {
        divisorSubstrings(240, 2);
    }

    //Q1
    public static int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int n = s.length();
        int ans = 0;
        for (int i = k; i <= n; i++) {
            String sub = s.substring(i - k, i);
            Integer val = Integer.parseInt(sub);
            if (val != 0 && num % val == 0) ans++;
//            System.out.println(sub);
        }


        return ans;

    }

    //Q2
    public static int waysToSplitArray(int[] nums) {
        int ans = 0;

        int n = nums.length;
//        int[] sum = new int[n + 1];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        long left = 0;
        long right = sum;
        for (int i = 0; i < n - 1; i++) {
            left += nums[i];
            right -= nums[i];
            if (left >= right) ans++;
        }


        return ans;
    }

    //Q3
    public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
//        int ans = 0;
//        Arrays.sort(tiles, (o1, o2) -> o1[0] - o2[0]);

        int n = tiles.length;
        int l = 0, r = 0;
        int sum = 0, ans = 0;
        while (l <= r && r < n) {
            int lb = tiles[l][0], rb = lb + carpetLen - 1;
            if (tiles[r][1] <= rb) {
                sum += tiles[r][1] - tiles[r][0] + 1;
                r++;
                ans = Math.max(sum, ans);
            } else {
                if (rb >= tiles[r][0]) {
                    ans = Math.max(ans, sum + rb - tiles[r][0] + 1);
                }
                sum -= tiles[l][1] - tiles[l][0] + 1;
                l++;
            }

        }


        return ans;
    }
}
