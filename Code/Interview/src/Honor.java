import javax.naming.spi.DirObjectFactory;
import java.util.*;

public class Honor {
    public static void main(String[] args) {
        QQ3();
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
            for (int j = 0; j < m + 1; j++) {
            }
        }


    }


    //2022-8-23
    //Q1
    public static void QQ1() {
        class man {
            String name;
            Integer volt;

            public man(String name, Integer volt) {
                this.name = name;
                this.volt = volt;
            }
        }
        Scanner scanner = new Scanner(System.in);
        String[] name = scanner.nextLine().split(",");

        PriorityQueue<man> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.volt == o2.volt) {
                return o1.name.compareTo(o2.name);
            }
            return o2.volt - o1.volt;
        });
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            char[] chars = name[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (j == 0 && !Character.isUpperCase(chars[j])) {
                    System.out.println("error.0001");
                    return;
                }
                if (j != 0 && !Character.isLowerCase(chars[j])) {
                    System.out.println("error.0001");
                    return;
                }
            }

            hashMap.put(name[i], hashMap.getOrDefault(name[i], 0) + 1);
        }

        for (Map.Entry en : hashMap.entrySet()) {
            queue.add(new man((String) en.getKey(), (Integer) en.getValue()));
        }

        System.out.println(queue.peek().name);


    }

    public static void QQ2() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean exist = false;
        char[] digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        // 30
        for (int i = 2; i <= 16; i++) {
            String str = "";
            int num = n;
            while (num != 0) {
                int r = num % i;
                num /= i;
                str += digit[r];
            }
            if (check(str)) {
                exist = true;
                System.out.println(i);
            }
        }
        if (!exist) {
            System.out.println(-1);
        }
    }

    public static boolean check(String str) {
        int l = str.length();
        for (int i = 0; i <= l / 2; i++) {
            if (str.charAt(i) != str.charAt(l - i - 1)) {
                return false;
            }
        }
        return true;
    }


    public static int[] services = new int[1000];
    public static List<Integer> service = new ArrayList<>();

    public static int getIdx(String serviceName) {
        String idxStr = serviceName.substring(serviceName.length() - 2, serviceName.length());
        if (serviceName.startsWith("add")) {
            int idx = Integer.parseInt(idxStr);
            return ((idx - 1) / 2) * 50 + (idx % 2 == 0 ? 500 : 0) + 25;
        } else {
            return (Integer.parseInt(idxStr) - 1) * 50;
        }
    }

    public static int hash(String token) {
        char[] chars = token.toCharArray();
        int sum = 0;

        for (char c : chars) {
            sum += c;
        }
        return sum % 999;
    }

    public static int saveToken(String token) {
        int idx = hash(token);
        while (services[idx] != 1) {
            idx++;
            idx %= 1000;
        }
        return idx;
    }

    public static int saveOrKill(String serviceName, int status) {
        int idx = getIdx(serviceName);
        services[idx] = status;
        return idx;
    }

    public static void QQ3() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        for (int i = 1; i <= 20; i++) {
            services[(i - 1) * 50] = 1;
        }
        int commandIdx = Integer.parseInt(command.split(":")[0]);
        String line = command.split(":")[1];
        if (commandIdx == 1) {
            int idx = saveOrKill(line, 1);
            System.out.println(idx);
        } else if (commandIdx == 2) {
            int idx = saveToken(line);
            System.out.println(idx);
        } else if (commandIdx == 3) {
            String[] service = line.split(";")[0].split(",");
            String token = line.split(";")[1];
            for (String s : service) {
                saveOrKill(s, 0);
            }
            int idx = saveToken(token);
            System.out.println(idx);
        } else if (commandIdx == 4) {
            int idx = saveOrKill(line, 1);
            System.out.println(idx);
        } else {
            String server = line.split(";")[0];
            String token = line.split(";")[1];
            saveOrKill(server, 1);
            int idx = saveToken(token);
            System.out.println(idx);
        }
    }
}
