public class a537complexNumberMultiply {
    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
    }

    public static String complexNumberMultiply(String num1, String num2) {
        int real1 = 0, real2 = 0;
        int ima1 = 0, ima2 = 0;
        String[] split = num1.split("\\+");
        real1 = Integer.parseInt(split[0]);
        ima1 = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
        split = num2.split("\\+");
        real2 = Integer.parseInt(split[0]);
        ima2 = Integer.parseInt(split[1].substring(0, split[1].length() - 1));

        int ansReal = real1 * real2 - ima1 * ima2;
        int ansIma = real1 * ima2 + real2 * ima1;
        String ans = ansReal + "+" + ansIma + "i";
        return ans;


    }
}
