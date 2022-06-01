import java.util.*;

public class HUAWEI {
    public static void main(String[] args) {
        Q11();
//        Q2();
    }

    // Q1
    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        class node {
            int beg;
            int end;
            int val;
        }
        node[] num = new node[m];
        for (int i = 0; i < m; i++) {
            node temp = new node();
            temp.beg = scanner.nextInt();
            temp.end = scanner.nextInt();
            temp.val = scanner.nextInt();
            num[i] = temp;
        }
        Arrays.sort(num, (o1, o2) -> {
            if (o1.beg == o2.beg)
                return o1.end - o2.end;
            else
                return o1.beg - o2.beg;
        });
        PriorityQueue<node> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        int ans = 0;
        int j = 0;
        for (int i = 1; i <= n; i++) {
            while (j < m && num[j].beg <= i) {
                queue.add(num[j]);
                j++;
            }
            Iterator<node> it = queue.iterator();
            while (it.hasNext()) {
                node temp = it.next();
                if (temp.end < i) it.remove();
            }
            ans += queue.peek().val;
        }
        System.out.println(ans);

    }

    // Q2
    public static void Q2() {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        scanner.nextLine();
        for (int m = 0; m < M; m++) {
            String formula = scanner.nextLine();
            int i = formula.indexOf('*');
            String num1 = formula.substring(0, i);
            int k = formula.indexOf('=');
            String num2 = formula.substring(i + 1, k);
            String num3 = formula.substring(k + 1);
            if (Integer.parseInt(num1) * Integer.parseInt(num2) == Integer.parseInt(num3))
                System.out.println("0");
            String str = num1 + num2 + num3;

            int len = num1.length() + num2.length() + num3.length();
//            bfs(str, num1.length(), num2.length(), num3.length(), );
        }
    }

    public static void bfs(String num, int idx1, int idx2, int idx3, int i) {


    }

//    public static boolean check() {
//
//    }

    //Q3
    public static void Q11() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] discount = new int[m][3];

        for (int i = 0; i < m; i++) {
            discount[i][0] = scanner.nextInt();
            discount[i][1] = scanner.nextInt();
            discount[i][2] = scanner.nextInt();
        }
        Arrays.sort(discount, (o1, o2) -> o1[2] - o2[2]);
        int[] price = new int[n + 1];
        Arrays.fill(price, Integer.MAX_VALUE);
        price[0] = 0;
        for (int[] ints : discount) {
            for (int j = ints[0]; j <= ints[1]; j++) {
                price[j] = Math.min(price[j], ints[2]);
            }
        }
        int ans = 0;
        for (int i : price) {
            ans += i;
        }
        System.out.println(ans);

    }


}
