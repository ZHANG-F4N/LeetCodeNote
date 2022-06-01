import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Tencent {
    public static void main(String[] args) {
//        System.out.println(q1(new int[]{1, 2, 3, 4, 5, 6, 7}));
        Q5();
    }


    //q1
    public static int q1(int[] num) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            list.add(num[i]);
        }
        while (list.size() != 1) {
            ArrayList<Integer> temp = new ArrayList<>();
            int n = list.size();
            int[] idx = countPrimes(n + 1);
            for (int i = 0; i < n; i++) {
                if (idx[i + 1] == 1) {
                    temp.add(list.get(i));
                }
            }
            list = temp;
        }
        return list.get(0);
    }

    public static int[] countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        isPrime[1] = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return isPrime;
    }


//    public static boolean isPrime(int x) {
//        for (int i = 2; i * i <= x; ++i) {
//            if (x % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }


    public static int maxPro;

    public static void Q5() {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        maxPro = Integer.MIN_VALUE;
        backTrace(0, m, 0, n, prices);
        System.out.println(maxPro);


    }


    public static void backTrace(int day, int money, int stone, int n, int[] prices) {
        if (day >= n) return;
        else if (day == n - 1) {
            if ((money + stone * prices[day]) > maxPro) {
                maxPro = money + stone * prices[day];
            }
            return;
        } else {
            if (money >= prices[day]) {
                backTrace(day + 1, money - prices[day], stone + 1, n, prices);
            }
            backTrace(day + 1, money, stone, n, prices);
            if (stone > 0) {
                backTrace(day + 1, money + prices[day], stone - 1, n, prices);
            }
        }
        return;
    }


}
