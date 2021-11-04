public class a73minEatingSpeed {
    public static void main(String[] args) {

        System.out.println(minEatingSpeed(new int[]{312884470}, 312884469));
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 6));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int r = 10_0000_0000;
        int l = 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (judge(piles, mid, h)) {
                r = mid - 1;
            } else {
                l = mid+1;
            }
        }
        return l;


    }

    public static boolean judge(int[] piles, int K, int h) {
        int time = 0;
        for (int pile : piles) {
            time += pile % K == 0 ? pile / K : pile / K + 1;
        }
        return time <= h;
    }
}
