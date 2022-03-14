import java.util.*;

public class newYear2022 {
    //    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        while (jIn.hasNext()) {
//            int a = jIn.nextInt();
//            int b = jIn.nextInt();
//            int c = jIn.nextInt();
//            if (a*a + b * b == c * c) System.out.println("YES");
//            else System.out.println("NO");
//        }
//    }
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        while (jIn.hasNext()) {
//            int year = jIn.nextInt();
//            double ans = year / (year + 1.0);
//            System.out.printf("%.6f",ans);
//        }
//    }
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        while (jIn.hasNext()) {
//            int n = jIn.nextInt();
//            for (int i = 0; i < n; i++) {
//                int number = jIn.nextInt();
//                if (i % 3 == 0 && i + 3 < n) {
//                    System.out.printf("%d ", number);
//                }else if (i % 3 == 0 ){
//                    System.out.printf("%d", number);
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        while (jIn.hasNext()) {
//            int n = jIn.nextInt();
//            int k = jIn.nextInt();
//            int w = jIn.nextInt();
//            if (w > n) System.out.println(0);
//            else if (w * 3 > k) System.out.println(0);
//            else {
//                int ans = 1;
//                int j = k;
//                for (int i = 0; i < 3 * w; i++) {
//                    ans *= j;
//                    j--;
//                }
//                System.out.println(ans);
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        while (jIn.hasNext()) {
//            int n = jIn.nextInt();// 宽
//            int m = jIn.nextInt();// 长
//            int a = jIn.nextInt();
//
//            int numN = (int) Math.ceil((n / (double) a));
//            int numM = (int) Math.ceil((m / (double) a));
//            System.out.println(numN * numM);
//
//        }
//    }
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        HashMap<Character, Character> hashMap = new HashMap<>();
//        hashMap.put('A', 'U');
//        hashMap.put('G', 'C');
//        hashMap.put('C', 'G');
//        hashMap.put('U', 'A');
//        while (jIn.hasNext()) {
//            int n = jIn.nextInt();// 长度为n
//            int k = jIn.nextInt();// 安全阀值为k
//            String up = jIn.next();
//            String down = jIn.next();
//            int ans = 0;
//            for (int i = 0; i < up.length(); i++) {
//                Character chU = up.charAt(i);
//                Character chD = down.charAt(i);
//                if (hashMap.get(chU) == chD) ans++;
//            }
//            if (ans<=k) System.out.println("NO");
//            else System.out.println("YES");
//        }
//    }
    public static void main(String[] args) {
        Scanner jIn = new Scanner(System.in);
        int n = jIn.nextInt();// 长度为n
        if (n == 0) System.out.println("");
        for (int i = 0; i < n; i++) {
            int num = jIn.nextInt();
            String result = Long.toBinaryString(num);
            System.out.println(result);
        }
    }

//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        int n = jIn.nextInt();// 长度为n
//        int[] a = new int[n + 1];
//        for (int i = 1; i <= n; i++) {
//            a[i] = jIn.nextInt();
//            a[i] += a[i - 1];
//        }
//        int q = jIn.nextInt();// 次数
//        for (int i = 0; i < q; i++) {
//            int l = jIn.nextInt();
//            int r = jIn.nextInt();
//            System.out.println(a[r] - a[l - 1]);
//        }
//    }

//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        int n = jIn.nextInt();// 长度为n
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        long ans = 0;
//        for (int j = 0; j < n; j++) {
//            int a = jIn.nextInt();
//            int w = jIn.nextInt();
//            ans += w;
//            if (hashMap.containsKey(a)) {
//                if (hashMap.get(a) < w) hashMap.put(a, w);
//            } else hashMap.put(a, w);
//        }
//
//        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
//            ans -= en.getValue();
//        }
//        System.out.println(ans);
//    }

    //gcd(a, b) = xor(a, b) = c， 则 c = a - b
    // 枚举 约数
//    public static void main(String[] args) {
//        Scanner jIn = new Scanner(System.in);
//        int n = jIn.nextInt();// 长度为n
//        int ans = 0;
//        for (int i = 1; i <= n / 2; i++) {
//            for (int j = i * 2; j <= n; j+=i) {
//                int b = j-i;
//                if((j^b)==i){
//                    ans++;
//                }
//            }
//        }
//        System.out.println(ans);
//    }
}
