import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class contest283 {
    public static void main(String[] args) {
//        System.out.println(cellsInRange("K1:L2"));

        System.out.println(minimalKSum(new int[]{1, 4, 25, 10, 25}, 2));
        System.out.println(minimalKSum(new int[]{5, 6}, 6));
        System.out.println(minimalKSum(new int[]{96, 44, 99, 25, 61, 84, 88, 18, 19, 33, 60, 86, 52, 19, 32, 47,
                35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84}, 35));
    }

    public static List<String> cellsInRange(String s) {
        char col1 = s.charAt(0);
        int row1 = s.charAt(1) - '0';
        char col2 = s.charAt(3);
        int row2 = s.charAt(4) - '0';
        ArrayList<String> ans = new ArrayList<>();
        for (int i = col1; i <= col2; i++) {
            for (int j = row1; j <= row2; j++) {
                ans.add(String.valueOf((char) i) + String.valueOf(j));
            }
        }
        return ans;
    }

    public static long minimalKSum(int[] nums, int k) {
        HashSet<Long> set = new HashSet<>();
        for (int num : nums) {
            set.add((long)num);
        }
        int add = 0;
        long i = 1;
        long ans = 0l;
        while (add < k) {
            if (!set.contains(i)){
                ans += i;
                add++;
            }
            i++;
        }
        return ans;
//        for (int i = 1; i <)
//        int max = 0;
//        int min = Integer.MIN_VALUE;
//        long sum = 0l;
//        for (int num : nums) {
//            sum += num;
//            if (num < min) min = num;
//            if (num > max) max = num;
//        }
//        long ans = 0l;
//
//
//        if (min > k) {
//            ans = ((1 + k) * k) >> 1;
//        } else if (k < max && k >= min) {
//            ans = ()
//        } else {
//
//        }
//
//
//        return ans;

    }
}
