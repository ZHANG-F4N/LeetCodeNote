import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PDD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int q = 0; q < T; q++) {
            int n = scanner.nextInt();
            int v = scanner.nextInt();
            // k,a,b
            int[][] kab = new int[n][3];
            for (int i = 0; i < n; i++) {
                kab[i][0] = scanner.nextInt();
                kab[i][1] = scanner.nextInt();
                kab[i][2] = scanner.nextInt();
            }
            Arrays.sort(kab, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] != o2[1]) return o1[1] - o2[1];
                    else return o1[2] - o1[2];
                }
            });
            class node {
                int remain;
                int a;
                int b;
                public node(int remain, int a, int b) {
                    this.remain = remain;
                    this.a = a;
                    this.b = b;
                }
            }
            PriorityQueue<node> nodes = new PriorityQueue<>(new Comparator<node>() {
                @Override
                public int compare(node o1, node o2) {
                    if (o1.a != o2.a) return o1.a - o2.a;
                    else return o1.b - o2.b;
                }
            });
            int ans = 0;
            int l = 0;
            int r = 0;
            for (int i = 1; i <= 3000; i++) {
                while (r < n && kab[r][2] >= i && kab[r][1] <= i) {
                    nodes.add(new node(kab[r][0], kab[r][1], kab[r][2]));
                    r++;
                }
                while (l < n && !nodes.isEmpty() && nodes.peek().b < i) {
                    nodes.poll();
                    l++;
                }
                int oneDay = 0;
                while (oneDay != v && !nodes.isEmpty()) {
                    node temp = nodes.poll();
                    int min = Math.min(v - oneDay, temp.remain);
                    oneDay += min;
                    temp.remain -= min;
                    if (temp.remain != 0) {
                        nodes.offer(temp);
                    }
                }
                ans += oneDay;
            }
            System.out.println(ans);
        }

    }


    //Q2
    public static void Q2() {
//        Scanner scanner = new Scanner(System.in);
//        int k = scanner.nextInt();
//        String s = scanner.next();
//        char[] chars = s.toCharArray();
//        int n = chars.length;
//        int[] ans = new int[n];
//        Arrays.fill(ans, -1);
//        for (int i = 0; i < n; i++) {
//            if (chars[i] == '0') {
//                if (i - k >= 0) ans[i - k] = 0;
//                if (i + k < n) ans[i + k] = 0;
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            if (chars[i] == '1') {
//                if (i - k >= 0 && i + k < n) {
//                    if (ans[i - k] == 0) ans[i + k] = 1;
//                    if (ans[i + k] == 0) ans[i - k] = 1;
//                    if (ans[i - k] == -1 && ans[i + k] == -1) {
//                        ans[i - k] = 0;
//                        ans[i + k] = 1;
//                    }
//                } else if (i - k < 0) {
//                    if (i + k < n) ans[i + k] = 1;
//                } else if (i + k >= n) {
//                    if (i - k >= 0) ans[i - k] = 1;
//                }
//            }
//        }
//        StringBuilder strAns = new StringBuilder();
//        for (int i = 0; i < ans.length; i++) {
//            strAns.append(ans[i]);
//        }
//        System.out.println(strAns);
    }

    //Q3
    public static void Q3() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int q = 0; q < T; q++) {
            int n = scanner.nextInt();
            int v = scanner.nextInt();
            // k,a,b
            int[][] kab = new int[n][3];
            for (int i = 0; i < n; i++) {
                kab[i][0] = scanner.nextInt();
                kab[i][1] = scanner.nextInt();
                kab[i][2] = scanner.nextInt();
            }
            Arrays.sort(kab, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1] != o2[1]) return o1[1] - o2[1];
                    else return o1[2] - o1[2];
                }
            });
            class node {
                int remain;
                int a;
                int b;

                public node(int remain, int a, int b) {
                    this.remain = remain;
                    this.a = a;
                    this.b = b;
                }
            }
            PriorityQueue<node> nodes = new PriorityQueue<>(new Comparator<node>() {
                @Override
                public int compare(node o1, node o2) {
                    if (o1.a != o2.a) return o1.a - o2.a;
                    else return o1.b - o2.b;
                }
            });
            int ans = 0;
            int l = 0;
            int r = 0;
            for (int i = 1; i <= 3000; i++) {
                while (r < n && kab[r][2] >= i && kab[r][1] <= i) {
                    nodes.add(new node(kab[r][0], kab[r][1], kab[r][2]));
                    r++;
                }
                while (l < n && !nodes.isEmpty() && nodes.peek().b < i) {
                    nodes.poll();
                    l++;
                }
                int oneDay = 0;
                while (oneDay != v && !nodes.isEmpty()) {
                    node temp = nodes.poll();
                    int min = Math.min(v - oneDay, temp.remain);
                    oneDay += min;
                    temp.remain -= min;
                    if (temp.remain != 0) {
                        nodes.offer(temp);
                    }
                }
                ans += oneDay;
            }
            System.out.println(ans);
        }
    }
}
