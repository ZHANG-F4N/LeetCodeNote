import java.util.*;

public class BILIBILI082601 {
    public static void main(String[] args) {
//        int i = 0;
//        Integer j = new Integer(0);
//        System.out.println(i == j);
//        System.out.println(j.equals(i));
        Q1();
        Q2();
    }

    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals("A")) {
                num++;
                ans.add("12");
                ans.add("34");
            } else if (arr[i].equals("B")) {
                num++;
                ans.add("AB");
                ans.add("CD");
            } else {
                ans.add(arr[i]);
            }
        }
        System.out.print(num + " ");
        for (String an : ans) {
            System.out.print(an + " ");
        }
    }

    public static void Q2() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] l1 = line.split(",");
        String line1 = scanner.nextLine();
        String[] l2 = line1.split(",");
        int num = l1.length;
        class node {
            int m;// 成本
            int n;
            int bef;
        }
        PriorityQueue<node> nodes = new PriorityQueue<>((o1, o2) -> o1.m - o2.m);
        for (int i = 0; i < num; i++) {
            node t = new node();
            t.m = Integer.parseInt(l1[i]);
            t.n = Integer.parseInt(l2[i]);
            t.bef = t.n - t.m;
            nodes.offer(t);
        }
        int ben = scanner.nextInt();
        Iterator<node> it = nodes.iterator();
        while (it.hasNext()) {
            node t = it.next();
            if (ben >= t.m && t.bef > 0) {
                ben += t.bef;
            }
        }
        System.out.println(ben);
    }
}
