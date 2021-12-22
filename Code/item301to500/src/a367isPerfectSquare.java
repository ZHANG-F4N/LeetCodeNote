public class a367isPerfectSquare {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(2147483647));
        System.out.println(isPerfectSquare(1));

        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(16));
    }

    public static boolean isPerfectSquare(int num) {

        int l = 1;
        int r = num;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            long val = (long)mid * mid;
            if (val == num) {
                return true;
            }
            if (val > num) {
                r = mid - 1;
            }
            if (val < num) {
                l = mid + 1;
            }

        }

        return false;

    }
}
