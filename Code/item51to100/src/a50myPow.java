public class a50myPow {
    public static void main(String[] args) {
        System.out.println(myPow(2.00000, -2147483648));
    }

    public static double myPow(double x, int n) {
        double ans = 0.0;
        if (n == 0) {
            return 1.0;
        }
        //防止n=-2147483648,会溢出,直接传值会出错
        ans = Pow(x, Math.abs((long)n));
        if (n < 0) {
            ans = 1 / ans;
        }

        return ans;
    }

    public static double Pow(double x, long k) {
        if (k == 0) {
            return 1.0;
        } else {

            double temp = Pow(x, k >> 2);
            //如果直接return Pow(x, k / 2)*Pow(x, k / 2) 时间会翻倍
            if (k % 2 == 1) {
                return x * temp * temp;
            } else {
                return temp * temp;
            }
        }
    }
}
