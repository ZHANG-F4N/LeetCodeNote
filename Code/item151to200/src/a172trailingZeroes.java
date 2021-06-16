public class a172trailingZeroes {
    public static void main(String[] args) {
        int n = 125;
        System.out.println(trailingZeroesLogn(n));
    }

    //  11! = 11 * 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1
    //      = 11 * (2 * 5) * 9 * (4 * 2) * 7 * (3 * 2) * (1 * 5) * (2 * 2) * 3 * (1 * 2) * 1
    //  循环统计因子5的个数,然后加起来
    public static int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            int temp = i;
            while (temp % 5 == 0) {
                ans++;
                temp = temp / 5;
            }
        }
        return ans;
    }

    //直接算因子5的个数,毕竟每五个数产生一个5的倍数
    //
    public static int trailingZeroesLogn(int n) {
        //例如 25 的阶乘
        //比25小本身包含了5个 5 10 15 20 25
        //25 本身包含1个5因子 因为 25 = 5 * 5
        //125为例
        //125及以下有25个5的倍数
        //25及以下有5个5的倍数
        //5有1个
        //一共31个
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
