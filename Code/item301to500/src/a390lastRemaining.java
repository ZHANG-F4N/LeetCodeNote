public class a390lastRemaining {
    public static void main(String[] args) {
        System.out.println(lastRemaining(9));
    }

    public static int lastRemaining(int n) {
        int a1 = 1;
        int an = n;
        int ak = 1;
        int round = 1;
        int cnt = n;
        while (a1 != an) {
            if (round % 2 == 1) {
                a1 += ak;
                an = (cnt % 2 == 0) ? an : an - ak;
            } else {
                an -= ak;
                a1 = (cnt % 2 == 0) ? a1 : a1 + ak;
            }
            ak *= 2;
            cnt /= 2;
            round++;
        }
        return a1;
    }
}
