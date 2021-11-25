public class a43multiply {
    public static void main(String[] args) {
        //System.out.println(multiply("123456789", "9"));//"121932631112635269"
        System.out.println(multiply("123456789", "87654321"));//"121932631112635269"
        System.out.println(multiply("123456789", "987654321"));//"121932631112635269"
        System.out.println(multiply("9", "9"));
        System.out.println(multiply2("12345", "26"));
        System.out.println(multiply("123", "456"));
    }

    /*
     *
     *      12345
     *  x     126
     *      -----
     *      74070
     *     24690
     *     ------
     *     320970
     *    12345
     *  ---------
     *
     * */

    public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int ch1 = num2.charAt(i) - '0';

            int carry = 0;
            String val = "";
            for (int j = num1.length() - 1; j >= 0; j--) {
                int ch2 = num1.charAt(j) - '0';
                String temp = (ch1 * ch2 + carry) + val;
                if (temp.length() > (num1.length() - j)) {
                    carry = temp.charAt(0) - '0';
                    val = temp.substring(1);
                } else {
                    carry = 0;
                    val = temp;
                }
            }
            val = carry == 0 ? val : carry + val;
            // val + ans
            int cut = num2.length() - 1 - i;
            ans = plus(ans.substring(0, ans.length() - cut), val) + ans.substring(ans.length() - cut);
        }
        return ans;
    }

    public static String plus(String s1, String s2) {
        int carry = 0;
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int ch1 = s1.charAt(i) - '0';
            int ch2 = s2.charAt(j) - '0';
            int val = ch1 + ch2 + carry;
            carry = val >= 10 ? 1 : 0;
            ans.append(val % 10);
            i--;
            j--;
        }
        while (i >= 0) {
            int val = s1.charAt(i) + carry - '0';
            carry = val >= 10 ? 1 : 0;
            ans.append(val % 10);
            i--;
        }
        while (j >= 0) {
            int val = s2.charAt(j) + carry - '0';
            carry = val >= 10 ? 1 : 0;
            ans.append(val % 10);
            j--;
        }
        if (carry != 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }
}
