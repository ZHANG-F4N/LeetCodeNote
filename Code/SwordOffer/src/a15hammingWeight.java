public class a15hammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(-3));
    }

    public static int hammingWeight(int n) {
        int ans = 0;
        //n & (n−1)，其预算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果
        //如：6&(6-1) = 4, 6 = (110), 4 = (100) 6 & (6−1)=4,6=(110) ，
        // 运算结果 4 即为把 6 的二进制位中的最低位的 1 变为 0 之后的结果。

        //这样我们可以利用这个位运算的性质加速我们的检查过程，
        // 在实际代码中，我们不断让当前的 n 与 n−1 做与运算，
        // 直到 n 变为 0 即可。因为每次运算会使得 n
        // n 的最低位的 1 被翻转，因此运算次数就等于 n 的二进制位中 1 的个数。
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                ans++;
            }
            n = n >> 1;
        }
        return ans;






    }
}
