import java.util.Scanner;

public class NetEase20220327 {
    public static void main(String[] args) {
        Q1();
    }

    private static void Q1v2() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        int ans = 0;
        int max = Math.max(x, y);
        while (a > 0 && b > 0) {
            if (2 * y > x) {
                a -= y;
                b -= y;
            } else {
                a -= x;
                b -= x;
            }
            ans += 2;
        }
        if (a > 0) ans += a % max == 0 ? a / max : a / max + 1;
        if (b > 0) ans += b % max == 0 ? b / max : b / max + 1;
        System.out.println(ans);
    }


    // Q1
    public static int ans;

    private static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        ans = Integer.MAX_VALUE;
        backtrace(a, b, x, y, 0);
        System.out.println(ans);


    }

    public static void backtrace(int a, int b, int x, int y, int num) {
        if (a <= 0 && b <= 0) {
            if (num < ans) ans = num;
            return;
        }
        if (num > ans) return;
        if (a > 0) backtrace(a - x, b, x, y, num + 1);
        if (b > 0) backtrace(a, b - x, x, y, num + 1);
        backtrace(a - y, b - y, x, y, num + 1);
    }


    // Q2
    private static void Q2() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int[] score = new int[26];
        char[] chars = str.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) {


        }


    }
}
