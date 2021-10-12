import java.util.stream.IntStream;

public class a29divide {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.divide(10, 3));
        System.out.println(divide(1, 10));
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            // 最大负数绝对值溢出
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        }
        int INF = Integer.MAX_VALUE;
        long a = dividend, b = divisor;
        boolean flag = (dividend ^ divisor) < 0;//结果符号 xor
//        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
//            flag = false;
//        }
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        long left = 0;
        long right = a;
        while (left < right) {
            long mid = left + ((right - left + 1) >> 1);
            if (mul(b, mid) <= a) {
                left = mid;
            } else if (mul(b, mid) > a) {
                right = mid - 1;
            }
        }
        if (flag) {
            left = -left;
        }
        if (left > INF || left < -INF - 1) {
            return INF;
        }

        return (int) left;

    }

    // 快速幂和快速乘 方法类似

    //快速乘
    public static long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) {
                ans += a;
            }
            k >>= 1;
            a += a;
        }
        return ans;
    }
    //快速幂

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
}

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        }
        int num = 0;
        boolean negative = (dividend ^ divisor) < 0;
        // System.out.println("negative:"+negative);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        // System.out.println(a+","+b);
//        while(a>=b){
//            a-=b;
//            num+=1;
//        }
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {
                num += 1 << i;//???????
                a -= b << i;//????
            }
        }
        if (negative) {
            num = -num;
        }
        return num;
    }
}
