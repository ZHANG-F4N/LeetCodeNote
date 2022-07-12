public class a1175numPrimeArrangements {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(numPrimeArrangements(5) );
        System.out.println(numPrimeArrangements(10) );
        System.out.println(numPrimeArrangements(100));
    }

    public static int numPrimeArrangements(int n) {
        int num = isPrime(n);
        return (int) (factorial(num) * factorial(n - num) % MOD);
    }


    public static int isPrime(int n) {
        int[] flag = new int[n + 1];
        int num = 0;
        for (int i = 2; i <= n; i++) {
            if (flag[i] == 0) {
                num++;
                for (int j = i * i; j <= n; j += i) {
                    flag[j] = 1;
                }
            }
        }

        return num;
    }

    private static long factorial(int n) {
        int ret = 1;
        while (n > 1) {
            ret = (int) (1L * ret * n % MOD);
            n--;
        }
        return ret;
    }
}
