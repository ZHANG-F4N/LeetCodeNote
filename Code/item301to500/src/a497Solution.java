import java.util.Random;

public class a497Solution {
    public static void main(String[] args) {
        Solution obj = new Solution(new int[][]{{-2, -2, 1, 1}, {2, 2, 4, 6}});
        int[] param_1 = obj.pick();
    }

    static class Solution {
        public int[] preSum;
        private int[][] rc;
        public int n;
        Random random = new Random();

        public Solution(int[][] rects) {
            rc = rects;
            n = rects.length;
            preSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] =
                        preSum[i] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            }
        }

        public int[] pick() {
            int val = random.nextInt(preSum[n]) + 1;// 决定第几个矩形
            // 二分是哪个矩形
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (preSum[mid] >= val) r = mid;
                else l = mid + 1;
            }
            int[] cur = rc[r - 1];
            int x = random.nextInt(cur[2] - cur[0] + 1) + cur[0];
            int y = random.nextInt(cur[3] - cur[1] + 1) + cur[1];
            return new int[]{x, y};

        }
    }

}
