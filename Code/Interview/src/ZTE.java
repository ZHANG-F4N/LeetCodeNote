import java.util.*;

public class ZTE {
    public static void main(String[] args) {
        Q5();
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()) {
//            String a = in.nextLine();
//            char[] arr = a.toCharArray();
//            int num = 0;
//            for (char c : arr) {
//                if (c == 'T') num++;
//            }
//            if ((num & 1) == 1) {
//                System.out.println(0);
//            }
//            int numT = 0;
//            int numS = 0;
//            long ans = 1L;
//            boolean f = false;
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i] == 'T') {
//                    numT++;
//                    if ((numT & 1) == 1) {
//                        f = true;
//                        if (numS != 0) ans *= numS + 1;
//                        numS = 0;
//                    } else {
//                        f = false;
//                    }
//                } else {
//                    if (!f) numS++;
//                }
//            }
//            System.out.println(ans);
//        }
    }

    public static void Q1() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String a = in.nextLine();
            String[] s = a.split(" ");
            int[] val = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                val[i] = Integer.parseInt(s[i]);
            }
            int n = s.length;
            int[] dp = new int[n];
            dp[0] = val[0];
            dp[1] = Math.max(val[0], val[1]);
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(val[i] + dp[i - 2], dp[i - 1]);
            }
            System.out.println(dp[n - 1]);
        }
    }

    public static void Q2() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.computeIfAbsent(1,val->10);
        long cost = 0;
        int i = n - 1;
        while (i >= 2) {
            long skill = Math.min(arr[i - 1] / 2, arr[i - 2] / 3);
            skill = Math.min(skill, arr[i]);
            cost += skill * 5;
            arr[i] -= skill;
            arr[i - 1] -= skill * 2;
            arr[i - 2] -= skill * 3;
            cost += arr[i];
            arr[i] = 0;
            while (i < arr.length && arr[i] == 0) i--;

        }
        cost += arr[0];
        cost += arr[1];
        System.out.println(cost);

    }


    public static void Q3() {

        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(",");
        int n = split.length;
        int[][] P = new int[n][2];

        for (int i = 0; i < split.length; i++) P[i][0] = Integer.parseInt(split[i]);
        split = scanner.nextLine().split(",");
        for (int i = 0; i < split.length; i++) P[i][1] = Integer.parseInt(split[i]);
        if (n < 3) {

        }
        Arrays.sort(P, (o1, o2) -> o1[1] == o2[1] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int ans = 0;
        int num = n - n % 3;
        for (int i = 0; i < num; i += 3) {
            int valA = P[i][0] + P[i + 1][0] + P[i + 2][0];
            valA = (int) (valA * 0.6);
            int valB = P[i][0] + P[i + 1][0];
            if (valA > valB) ans += valB;
            else ans += valA;
        }
        System.out.println(ans);
    }


    public static void Q4() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] s1 = s.split(" ");
        char[] arr1 = s1[0].toCharArray();
        char[] arr2 = s1[1].toCharArray();
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();

        for (int i = 0; i < arr1.length; i++) {
            set1.add(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            set2.add(arr2[i]);
        }
        for (Character ch : set2) {
            if (!set1.contains(ch)) {
                System.out.println(ch);
                return;
            }
        }

        return;
    }

    public static void Q5() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int K = scanner.nextInt()-1;
        int[][] map = new int[n][n];
        String line = "";
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i],100000);
        }
        while (!(line = scanner.nextLine()).equals("")) {
            String[] xyt = line.split(" ");
            int x = Integer.parseInt(xyt[0]);

            int y = Integer.parseInt(xyt[1]);
            int t = Integer.parseInt(xyt[2]);
            map[x - 1][y - 1] = t;
        }
        int[] shortPath = new int[n]; // 保存start到其他各点的最短路径
        String[] path = new String[n]; // 保存start到其他各点最短路径的字符串表示
        for (int i = 0; i < n; i++)
            path[i] = new String(K + "-->" + i);
        int[] visited = new int[n]; // 标记当前该顶点的最短路径是否已经求出,1表示已求出

        // 初始化，第一个顶点已经求出
        shortPath[K] = 0;
        visited[K] = 1;

        for (int count = 1; count < n; count++) { // 要加入n-1个顶点
            int k = -1; // 选出一个距离初始顶点start最近的未标记顶点
            int dmin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && map[K][i] < dmin) {
                    dmin = map[K][i];
                    k = i;
                }
            }

            // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;

            // 以k为中间点，修正从start到未访问各点的距离
            for (int i = 0; i < n; i++) {
                //如果 '起始点到当前点距离' + '当前点到某点距离' < '起始点到某点距离', 则更新
                if (visited[i] == 0 && map[K][k] + map[k][i] < map[K][i]) {
                    map[K][i] = map[K][k] + map[k][i];
                    path[i] = path[k] + "-->" + i;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("从" + K + "出发到" + i + "的最短路径为：" + path[i]);
        }


    }

}
