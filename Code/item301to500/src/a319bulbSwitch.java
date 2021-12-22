public class a319bulbSwitch {
    public static void main(String[] args) {
        System.out.println(bulbSwitch(1));
        System.out.println(bulbSwitch(100));
    }

    public static int bulbSwitch(int n) {

        /*
        *   1   2   3   4   5   6   7   8   9   10  11
     1  *   *   *   *   *   *   *   *   *   *   *   *
     2  *   *   -   *   -   *   -   *   -   *   -   *
     3  *   *   -   -   -   *   *   *   -   -   -   *
     4  *   *   -   -   *   *   *   *   *   -   -   *
     5  *   *   -   -   *   -   *   *   *   -   *   *
     6  *   *   -   -   *   -   -   *   *   -   *   *
     7  *   *   -   -   *   -   -   -   *   -   *   *
     8  *   *   -   -   *   -   -   -   -   -   *   *
     9  *   *   -   -   *   -   -   -   -   *   *   *
    10  *   *   -   -   *   -   -   -   -   *   -   *
    11  *   *   -   -   *   -   -   -   -   *   -   -
        * */
        // 规律 n的平方最后总会亮
//        int ans = 0;
//        if (n == 0) return 0;
//        for (int i = 1; i < 100000; i++) {
//            if (i * i > n) {
//                break;
//            }
//            if (i * i <= n) {
//                ans++;
//            }
//        }
        return (int) Math.sqrt(n + 0.5);

    }
}
