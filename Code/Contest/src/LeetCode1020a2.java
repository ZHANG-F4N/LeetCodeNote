import java.util.Arrays;

public class LeetCode1020a2 {
    public static void main(String[] args) {
        System.out.println(winnerOfGame("ABBBBBBBAAA"));
    }

    //  A   A   A   B   A   B   B
    //
    public static boolean winnerOfGame(String colors) {

//        char ch = 'A';
//        StringBuilder temp = new StringBuilder(colors);
//        int index = judge(temp, ch);
//        while (index != -1) {
//            //拼接
//            temp = new StringBuilder(temp.substring(0, index) + temp.substring(index + 1, temp.length()));
//            ch = ch == 'A' ? 'B' : 'A';
//            index = judge(temp, ch);
//        }
//
//        return ch == 'A' ? false : true;
        int ansA = judge(colors, 'A');
        int ansB = judge(colors, 'B');
        return ansA > ansB ? true : false;


    }

    public static int judge(String colors, char ch) {
        if (colors.length() <= 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0, j = 1, k = 2; k < colors.length(); i++, j++, k++) {
            if (colors.charAt(i) == ch && colors.charAt(j) == ch && colors.charAt(k) == ch) {
                ans++;
            }
        }
        return ans;

    }

}
