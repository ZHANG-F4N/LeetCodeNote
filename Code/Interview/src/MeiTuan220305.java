import java.util.Scanner;

public class MeiTuan220305 {
    public static void main(String[] args) {
        Q3();
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = arr[i - 1] - 1 == arr[i - 2] ? dp[i - 2] + 1 : dp[i - 1] + 1;
        }
        System.out.println(dp[num]);
    }

    public static void Q3() {
        Scanner scanner = new Scanner(System.in);
        int n = 0, m = 0;
        n = scanner.nextInt();
        m = scanner.nextInt();
        String[] dir = new String[m];
        int[] cut = new int[m];
        for (int i = 0; i < m; i++) {
            dir[i] = scanner.next();
        }
        for (int i = 0; i < m; i++) {
            cut[i] = scanner.nextInt();
        }
        int volume = n * n * n;
        int x = n, y = n, z = n;
        for (int i = 0; i < m; i++) {
            if (dir[i].equals("x")) {
                x = Math.max(x - cut[i], cut[i]);
            }
            if (dir[i].equals("y")) {
                y = Math.max(y - cut[i], cut[i]);
            }
            if (dir[i].equals("z")) {
                z = Math.max(z - cut[i], cut[i]);
            }
            volume = x * y * z;
            System.out.println(volume);
        }
    }
}
