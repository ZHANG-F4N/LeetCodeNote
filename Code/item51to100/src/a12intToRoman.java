import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class a12intToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(1000));
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(2994));
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (values[i] <= num) {
                ans.append(rom[i]);
                num -= values[i];
            }
        }

        return ans.toString();


    }
}
