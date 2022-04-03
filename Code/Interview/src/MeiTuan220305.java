import java.util.Scanner;

public class MeiTuan220305 {
    public static void main(String[] args) {
        Q4();
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


    public static void Q4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] pre = new int[n];
        int[] preSum = new int[n];
        pre[0] = arr[0];
        int[] end = new int[n];
        int[] endSum = new int[n];
        end[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            if (pre[i - 1] < arr[i]) {
                pre[i] = arr[i];
                preSum[i] = preSum[i - 1];
            } else {
                pre[i] = pre[i - 1] + 1;
                preSum[i] = preSum[i - 1] + pre[i] - arr[i];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < end[i + 1]) {
                end[i] = end[i + 1] + 1;
                endSum[i] = endSum[i] + end[i] - arr[i];
            } else {
                end[i] = arr[i];
                endSum[i] = endSum[i + 1];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            int max = Math.max(pre[i], end[i]);
            ans = Math.min(ans, max - arr[i] + preSum[i - 1] + endSum[i + 1]);
        }
        System.out.println(ans);
    }
}
