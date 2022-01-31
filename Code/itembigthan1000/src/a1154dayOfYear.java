import javax.print.DocFlavor;

public class a1154dayOfYear {
    public static void main(String[] args) {
        System.out.println(dayOfYear("2019-01-09"));
        System.out.println(dayOfYear("2004-03-01"));
        System.out.println(dayOfYear("2003-03-01"));
    }

    public static int dayOfYear(String date) {

        int[] mouth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.valueOf(date.substring(0, 4));
        int mou = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));

        int ans = 0;
        if (mou == 1) {
            return day;
        } else if (mou == 2) {
            return 31 + day;
        } else {
            for (int i = 0; i < mou - 1; i++) {
                ans += mouth[i];
            }
            if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
                return ans + day + 1;
            } else {
                return ans + day;
            }
        }
    }
}
