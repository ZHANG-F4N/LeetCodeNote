public class a415addStrings {
    public static void main(String[] args) {
        System.out.println(addStrings("456", "77"));
    }

    public static String addStrings(String num1, String num2) {
        int M = num1.length() - 1;
        int N = num2.length() - 1;
//        int len = M > N ? M : N;
//        int[] ans = new int[len + 1];
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        while (M >= 0 && N >= 0) {
            int ch1 = num1.charAt(M) - '0';
            int ch2 = num2.charAt(N) - '0';
            int sum = ch1 + ch2 + carry;
            if (sum >= 10) {
                carry = 1;
                ans.append(sum % 10);
            } else {
                carry = 0;
                ans.append(sum);
            }
            M--;
            N--;
        }
        while (M >= 0) {
            int sum = num1.charAt(M) - '0' + carry;
            if (sum >= 10) {
                carry = 1;
                ans.append(sum % 10);
            } else {
                carry = 0;
                ans.append(sum);
            }
            M--;
        }
        while (N >= 0) {
            int sum = num2.charAt(N) - '0' + carry;
            if (sum >= 10) {
                carry = 1;
                ans.append(sum % 10);
            } else {
                carry = 0;
                ans.append(sum);
            }
            N--;
        }
        if (carry == 1) {
            ans.append("1");
        }
        return ans.reverse().toString();
    }
}
