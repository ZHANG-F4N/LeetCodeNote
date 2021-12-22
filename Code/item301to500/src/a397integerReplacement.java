public class a397integerReplacement {
    public static void main(String[] args) {
        System.out.println(integerReplacement(65535));
        System.out.println(integerReplacement(4));
    }

    public static int integerReplacement(int n) {
        int ans = 0;
        // x 为奇数所能执行的两种操作，+1 能够消除连续一段的 1，
        // 只要次低位为 1（存在连续段），应当优先使用 +1 操作，
        // 但需要注意边界 x = 3 时的情况（此时选择 -1 操作）。
        //
        long _n = n;
        while (_n != 1) {
            if ((_n & 1) == 0)  _n >>= 1;
            else {
                if (_n != 3 && ((_n >> 1) & 1) == 1) _n++;
                else _n--;
            }
            ans++;
        }

        return ans;
    }
}
