import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.HashSet;

public class contest80 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(successfulPairs(new int[]{5, 1, 3}, new int[]{1, 2, 3, 4, 5}, 7
        )));

    }

    //Q1
    public static boolean strongPasswordCheckerII(String password) {

        if (password.length() < 8) return false;
        char[] chars = password.toCharArray();
        boolean little = false, big = false, number = false, special = false;
        String spe = "!@#$%^&*()-+";
        char pre = ' ';
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)) number = true;
            if (Character.isLowerCase(ch)) little = true;
            if (Character.isUpperCase(ch)) big = true;
            if (spe.indexOf(ch) != -1) special = true;
            if (i != 0) {
                if (pre == ch) return false;
            }
            pre = ch;

        }
        if (!little) return false;
        if (!number) return false;
        if (!big) return false;
        if (!special) return false;
        return true;
    }

    //Q2
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {

//        Arrays.sort(spells);
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int val = spells[i];
            int l = 0, r = m - 1;
            while (l <= r) {
                int mid = l + r >> 1;
                if ((long) val * potions[mid] < success) {
                    l = mid + 1;
                } else {
                    r = mid-1;
                }
            }
            ans[i] = m - l;
        }
        return ans;
    }

    //Q3



}
