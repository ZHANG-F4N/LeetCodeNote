import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Honor20220816 {
    public static void main(String[] args) {
        Q2();
    }

    //Q1
    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        String[] mstr = scanner.nextLine().split(",");
        String[] nstr = scanner.nextLine().split(",");
        int k = scanner.nextInt();
        int num = mstr.length;
        class Fruit {
            int cost;
            int price;

            public Fruit(int cost, int price) {
                this.cost = cost;
                this.price = price;
            }
        }
        PriorityQueue<Fruit> fruits = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int i = 0; i < num; i++) {
            fruits.add(new Fruit(Integer.parseInt(mstr[i]), Integer.parseInt(nstr[i])));
        }
        while (!fruits.isEmpty() && k >= fruits.peek().cost) {
            Fruit top = fruits.poll();
            k += top.price - top.cost;
        }

        System.out.println(k);
    }
    //Q2

    public static void Q2() {
//        class Day {
//            int id;
//            int val;
//
//            public Day(int num, int val) {
//                this.id = num;
//                this.val = val;
//            }
//        }
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int p = 0; p < T; p++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            if (n == 1) {
                System.out.println("0 0");
                return;
            }
            int max = 0, power = 0;
            for (int i = 1; i < arr.length; i++) {
                int t = power;
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        t++;
                    } else if (arr[j] > arr[i]) {
                        t--;
                    }
                }
                if (t > max) max = t;
                power = t;
            }
            System.out.println(max + " " + power);
        }
    }

    // MEITUAN

    public static void Q4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] narr = new int[n];
        for (int i = 0; i < n; i++) {
            narr[i] = scanner.nextInt();
        }
        int[] marr = new int[m];
        for (int i = 0; i < m; i++) {
            marr[i] = scanner.nextInt();
        }
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = Math.abs(narr[i]);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = Math.abs(narr[i]);
        }




        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m+1; j++) {
            }
        }


    }

}
