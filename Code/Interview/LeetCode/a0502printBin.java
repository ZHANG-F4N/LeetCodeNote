public class a0502printBin {
    public static void main(String[] args) {
        System.out.println(printBin(0.1));
        System.out.println(printBin(0.625));
        System.out.println(printBin(0.72));
    }

    public static String printBin(double num) {
        String ans = "0.";
        for (int i = 0; i < 30; i++) {
            if (num == 0) {
                break;
            }
            num *= 2;
            if (num >= 1) {
                ans += "1";
                num = num - 1;
            } else {
                ans += "0";
            }
        }
        if (num != 0.0) {
            return "ERROR";
        }
        return ans;
    }
}
