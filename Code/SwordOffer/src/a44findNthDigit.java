public class a44findNthDigit {
    public static void main(String[] args) {
        System.out.println(isUgly(0));
    }

    public static int findNthDigit(int n) {

        return 0;
    }

    public static boolean isUgly(int n) {
        if (n == 1) {
            return true;
        }
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else if (n % 3 == 0) {
                n /= 3;
            } else if (n % 5 == 0) {
                n /= 5;
            } else if (n == 1) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
