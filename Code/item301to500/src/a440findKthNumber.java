public class a440findKthNumber {
    public static void main(String[] args) {
        System.out.println(findKthNumber(13, 2));
    }

    public static int findKthNumber(int n, int k) {
        int ans = 1;
        while (k > 1) {
            // 从最小的前缀 11 开始枚举,假设当前枚举到前缀 x
            int cnt = getCnt(ans, n);
            // cnt<k：说明所有以 x 为前缀的数组均可跳过，此时让 x 自增，k 减去 cnt。
            // 含义为从下一个「数值比 x 大」的前缀中找目标值；
            if (cnt < k) {
                k -= cnt;
                ans++;
            } else {
                // cnt⩾k：说明目标值前缀必然为 x，此时我们需要在以 x 为前缀的前提下找目标值。
                // 此时让 x 乘 10，k 减 1（代表跳过了 x本身）。
                // 含义为从下一个「字典序比 x 大」的前缀中找目标值。
                k--;
                ans *= 10;
            }
        }
        return ans;
    }

    //    该函数实现了统计范围 [1,limit] 内以 x 为前缀的数的个数。
    static int getCnt(int x, int limit) {
        String a = String.valueOf(x), b = String.valueOf(limit);
        int n = a.length(), m = b.length(), k = m - n;
        int ans = 0;
        int u = Integer.parseInt(b.substring(0, n));
        for (int i = 0; i < k; i++) {
            ans += Math.pow(10, i);
        }
        if (u > x) ans += Math.pow(10, k);
        else if (u == x) ans += limit - x * Math.pow(10, k) + 1;
        return ans;
    }

}
