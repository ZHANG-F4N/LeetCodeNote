import java.util.Arrays;

public class a204countPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(2));
    }

    public static int countPrimes(int n) {
        if (n == 0 || n == 1) {
            return 0;
        }
        int sqrt_n = (int) Math.sqrt(n);
        int ans = 0;
        boolean[] isPrimes = new boolean[n];

        for (int i = 2; i < isPrimes.length; i++) {
            int temp = 2;
            if (!isPrimes[i] && i <= sqrt_n) {
                while (i * temp < n) {
                    isPrimes[i * temp] = true;
                    temp++;
                }
            }
            if (!isPrimes[i] ) {
                ans++;
            }
        }
        //System.out.println(Arrays.toString(isPrimes));
        return ans;
    }
}
