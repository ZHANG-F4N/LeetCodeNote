public class a258addDigits {
    public static void main(String[] args) {
        System.out.println(addDigits(10));
        System.out.println(addDigits2(38));
    }

    public static int addDigits(int num) {
        int ans = num;
        int temp = num;
        while (ans >= 10) {
            temp = ans;
            ans = 0;
            while (temp > 0) {
                ans += temp % 10;
                temp /= 10;
            }
        }
        return ans;
    }

    public static int addDigits2(int num) {
        if (num == 0) return 0;
        return num % 9 == 0 ? 9 : num % 9;
//        return (num - 1) % 9 + 1;
    }
}
