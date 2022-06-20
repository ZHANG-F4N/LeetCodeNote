public class a875minEatingSpeed {
    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{312884470}, 968709470));
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }

        // 速度最小的时候，耗时最长
        int left = 1;
        // 速度最大的时候，耗时最短
        int right = maxVal;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(piles, mid) > h) {
                // 耗时太多，说明速度太慢了，下一轮搜索区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int check(int[] piles, int k) {
        int ans = 0;
        for (int pile : piles) {
            ans += (pile + k - 1) / k;
        }
        return ans;
    }
}
