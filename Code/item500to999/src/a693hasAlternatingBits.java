public class a693hasAlternatingBits {
    public static void main(String[] args) {
        System.out.println(11);
        System.out.println(7);
    }

    public static boolean hasAlternatingBits(int n) {
        int f = 33;
        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) == 1) {
                f = i;
                break;
            }
        }

        for (int i = f - 1; i >= 0; i--) {
        }

        return false;


    }
}
