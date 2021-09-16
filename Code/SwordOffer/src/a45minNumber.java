import java.util.Arrays;
import java.util.Comparator;

public class a45minNumber {
    public static void main(String[] args) {
        int[] num = {3,30,34,5,9};
        System.out.println(minNumber(num));
    }

    public static String minNumber(int[] nums) {
        String[] number = new String[nums.length];

        for (int i = 0; i < number.length; i++) {
            number[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(number, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });
        StringBuilder ans = new StringBuilder();
        for (String s : number) {
            ans.append(s);
        }
        return ans.toString();

    }


}
