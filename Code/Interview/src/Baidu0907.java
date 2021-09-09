import java.math.BigInteger;
import java.util.Scanner;

public class Baidu0907 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String num = scanner.next();
            if (judge(num)) {
                System.out.println(num);
            } else {
                System.out.println(nextPerfect(num));
            }
        }
    }

    public static long nextPerfect(String num) {
        long nums = Long.valueOf(num);
        StringBuilder ans = new StringBuilder();
        while (nums > 0) {
            long temp = nums % 10;
            if (temp == 0) {
                nums -= 1;
                for (int i = 0; i < ans.length(); i++) {
                    ans.replace(i,i+1,"3");
                }
                continue;
            } else if (temp > 3) {
                for (int i = 0; i < ans.length(); i++) {
                    ans.replace(i,i+1,"3");
                }
                ans.append('3');
            } else if (temp > 0) {
                ans.append(temp);
            }
            nums /= 10;
        }
        return Long.parseLong(ans.reverse().toString());

    }


    public static boolean judge(String num) {
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            if (temp == '0' || temp > '3') {
                return false;
            }
        }
        return true;
    }
}
