public class a869reorderedPowerOf2 {
    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(46));
        System.out.println(reorderedPowerOf2(821));
        System.out.println(reorderedPowerOf2(10));
        System.out.println(reorderedPowerOf2(1024));
    }

    public static boolean reorderedPowerOf2(int n) {

        // 判断一个数 是不是二的整数幂
        // x & (-x) == x
        if ((n & (-n)) == n) {
            return true;
        }

        long[] table = new long[32];

        for (int i = 0; i < 32; i++) {
            table[i] = (long) 1 << i;
        }

        String string = Integer.toString(n);
//        int[] flag = new int[10];
        int len = string.length();
        for (int i = 0; i < 32; i++) {
            String str = Long.toString(table[i]);
            if (str.length() < len) continue;
            if (str.length() > len) break;

            int[] flag = new int[10];
            for (char ch : string.toCharArray()) {
                flag[ch - '0']++;
            }
            for (char ch : str.toCharArray()) {
                flag[ch - '0']--;
            }
            boolean ans = true;
            for (int f : flag) {
                if (f != 0) {
                    ans = false;
                }
            }
            if (ans) {
                return true;
            }
        }


        return false;


    }
}
