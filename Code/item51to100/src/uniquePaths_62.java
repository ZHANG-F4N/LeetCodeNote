public class uniquePaths_62 {
    public static void main(String[] args) {
        int m = 3, n = 7;
        System.out.println(uniquePaths(m, n));
    }

    public static int uniquePaths(int m, int n) {
        int min = Math.min(m - 1, n - 1);
        int total = m + n - 2;
        long ans = 1;
        for (int i = total, j = 1; i >= total - min + 1; i--, j++) {
            ans = ans * i / j;
        }
        return (int) ans;
    }
}


