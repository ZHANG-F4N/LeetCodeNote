public class a264nthUglyNumber {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int[] flag = new int[n + 1];
        flag[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = flag[p2] * 2;
            int num3 = flag[p3] * 3;
            int num5 = flag[p5] * 5;
            int min = Math.min(num2, Math.min(num3, num5));
            flag[i] = min;
            if (min == num2) {
                p2++;
            }
            if (min == num3) {
                p3++;
            }
            if (min == num5) {
                p5++;
            }
        }
        return flag[n];
    }
}
