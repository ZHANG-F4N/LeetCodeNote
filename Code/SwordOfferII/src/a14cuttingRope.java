public class a14cuttingRope {

    public static void main(String[] args) {
        System.out.println(cuttingRope(15));
    }

    public static int cuttingRope(int n) {
        final int MOD = 1000000007;
        int ans = 0;
        if (n <= 3) {
            return n-1;
        }
        int residue = n % 3;
        int quotient = n / 3;
        if (residue == 0) {
            ans = (int)remainder(3, quotient, MOD) % MOD;
        } else if (residue == 1) {
            ans = (int)((remainder(3, quotient - 1, MOD) * 4) % MOD);
        } else {
            ans = (int)(remainder(3, quotient, MOD) * 2) % MOD;
        }
        return ans;
    }

    //# 求 (x^a) % p —— 快速幂求余
    // 注意底数和返回是long
    public static long remainder(long num, int quotient, int MOD) {
        long rem = 1;
        while (quotient > 0) {
            if ((quotient & 1) == 1) {
                rem *=  num;
                rem %= MOD;
            }
            num *= num;
            num %= MOD;
            quotient >>= 1;
        }
        return rem;
    }
}
