import java.util.ArrayList;
import java.util.Scanner;

public class MIHAYO20220807 {


    public static void main(String[] args) {
        Q2();
    }

    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList[] map = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            map[v1].add(v2);
            map[v2].add(v1);
        }
        boolean[] visit = new boolean[n];


        int[] ans = new int[n];
        // 回溯
//        dfs();

        StringBuilder res = new StringBuilder();
        for (int an : ans) {
            if (an == 1) res.append('R');
            if (an == 2) res.append('G');
            if (an == 3) res.append('B');
        }
        System.out.println(res.toString());
    }


    public static void Q3() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] pkg = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pkg[i][j] = scanner.nextInt();
            }
        }


    }

    // 00 00 00 01
    // 00 00 00 10
    // 00 00 00 02
    // 00 00 00 20 消息头 16


    // -------------------
    // 00 00 00 11 消息体长度

    // 00 30 消息类型 21
    // ------------------------------
    // 00 02
    // 00 04
    // 12 34 56 78

    // 00 05
    // 00 03
    // ab cd ef

    // 00000001
    // 00000010
    // 00000002
    // 00000020
    // 00000011
    // 0030
    // 0002
    // 0004
    // 12345678
    // 00050003abcdef
    public static void Q2() {
        Scanner scanner = new Scanner(System.in);
        int Len = scanner.nextInt();
        int Tag = scanner.nextInt();
        String s = scanner.next();
        // 抛去消息头 32
        s = s.substring(32);
        int msglen = Integer.parseInt(s.substring(0, 8), 16);
        s = s.substring(8);
        if (msglen * 2 != s.length()) {
            System.out.println(-4);
            return;
        }
        int msgtype = Integer.parseInt(s.substring(0, 4), 16);
        if (msgtype < 1 || msgtype > 200) {
            System.out.println(-3);
            return;
        }

        s = s.substring(4);
        int idx = 0;
        boolean flag = false;
        int ans = 0;
        while (!s.equals("")) {
            if (s.length() < 4) {
                System.out.println(-2);
                return;
            }
            int tag = Integer.parseInt(s.substring(0, 4), 16);
            if (!flag && tag == Tag) {
                flag = true;
                ans = idx;
            }
            s = s.substring(4);
            if (s.length() < 4) {
                System.out.println(-2);
                return;
            }
            int l = Integer.parseInt(s.substring(0, 4), 16);
            s = s.substring(4);
            idx += 4 + l;
            if (s.length() < l * 2) {
                System.out.println(-2);
                return;
            }
            s = s.substring(l * 2);
        }
        if (!flag) {
            System.out.println(-1);
            return;
        }
        System.out.println(22 + ans);
    }
}
