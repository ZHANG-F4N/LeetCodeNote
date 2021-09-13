public class a1894chalkReplacer {
    public static void main(String[] args) {
        int[] chalk = {5,  1, 5};
        int k = 22;
        System.out.println(chalkReplacer(chalk, k));
    }

    public static int chalkReplacer(int[] chalk, int k) {
        int ans = 0;
        long sum = 0;
        for (int i = 0; i < chalk.length; i++) {
            sum += chalk[i];
        }
        if (sum == k) {
            return 0;
        }
        k = (int) (k % sum);

        for (int i = 0; i < chalk.length; i++) {
            if (k - chalk[i] == 0) {
                return i + 1;
            } else if (k - chalk[i] < 0) {
                return i;
            }
            k -= chalk[i];
        }
        return ans;
    }
}
