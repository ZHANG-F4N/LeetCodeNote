import java.util.HashMap;

public class a166fractionToDecimal {
    public static void main(String[] args) {
        int numerator = 4, denominator = 333;
        System.out.println(fractionToDecimal(numerator, denominator));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder ansStr = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            ansStr.append('-');
        }
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));

        ansStr.append(String.valueOf(dividend / divisor));//整数

        long remain = dividend % divisor;
        if (remain == 0) {
            return ansStr.toString();
        }
        ansStr.append('.');
        HashMap<Long, Integer> hashMap = new HashMap<>();

        while (remain != 0) {
            if (hashMap.containsKey(remain)) {
                ansStr.insert(hashMap.get(remain), "(");//这里输入字符'('报错
                ansStr.append(')');
                return ansStr.toString();
            }
            hashMap.put(remain, ansStr.length());

            ansStr.append(remain * 10 / divisor);
            remain = remain * 10 % divisor;
            //System.out.println(ansStr);

        }
        return ansStr.toString();
    }
}
