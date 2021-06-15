public class a171titleToNumber {
    public static void main(String[] args) {
        String title = "ABCD";
        System.out.println(titleToNumber(title));
    }

    public static int titleToNumber(String columnTitle) {
        //ZY = 26 * 26 + 25
        //AAA = 1*26*26 + 1*26 + 1 = (1*26+1)*26 + 1
        //ABCD = 1*26*26*26 + 2*26*26 + 3*26 + 4 = (((1)*26+2)*26+3)*26 + 4 = 19010
        int ans = 0;
        int index = columnTitle.length();
        for (int i = 0; i < index; i++) {
            ans = ans * 26 + columnTitle.charAt(i) - 'A' + 1;
        }
        return ans;
    }
}
