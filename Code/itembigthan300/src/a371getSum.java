public class a371getSum {
    public static void main(String[] args) {
        int a = -2;
        int b = 4;
        getSum(a, b);
    }

    public static int getSum(int a, int b) {
        //5 = 0101
        //4 = 0100
        //^-------
        //    0001
        //&-------
        //    0100
        int ans = a ^ b;
        int carry = (a & b) << 1;
        //负数会循环一个轮回
        while (carry != 0) {
            int temp = ans ^ carry;
            carry = (ans & carry)<<1;
            ans = temp;
        }
        return ans;
    }
}
