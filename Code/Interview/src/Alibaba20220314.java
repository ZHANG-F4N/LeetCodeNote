import java.util.Arrays;
import java.util.Scanner;

public class Alibaba20220314 {

    public static void main(String[] args) {
        int[] map = new int[26];
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char[] chars = str.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; i++) {
            map[chars[i] - 'a']++;
        }
        int ans = 0;
        int all = Arrays.stream(map).sum();
        for (int i = 0; i < 26; i++) {
            if (map[i] >= 2 && ans == 0) ans++;
            if (map[i] != 0) map[i] = all - map[i];

        }
        int tol = 0;
        for (int i = 0; i < n; i++) {
            tol += map[chars[i] - 'a'];
        }
        ans += tol / 2;
        System.out.println(ans);
    }
}

//    //Q2
//    public static void q1() {
//        int[] map = new int[26];
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
//
//        char[] chars = str.toCharArray();
//        int n = chars.length;
//
//        for (int i = 0; i < n; i++) {
//            map[chars[i] - 'a']++;
//        }
//        int all = Arrays.stream(map).sum();
//        for (int i = 0; i < 26; i++) {
//            if (map[i] != 0)
//            map[i] = all - map[i];
//        }
//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            ans += map[i];
//        }
//        ans = ans / 2 + 1;
//        System.out.println(ans);
//    }