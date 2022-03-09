import javax.print.DocFlavor;

public class a504convertToBase7 {
    public static void main(String[] args) {
        System.out.println(convertToBase7(101));
        System.out.println(convertToBase7(-7));
    }

    public static String convertToBase7(int num) {
        if (num == 0)return "0";
        StringBuilder ans = new StringBuilder();
        int temp = Math.abs(num);
        while (temp != 0) {
            ans.append(temp % 7);
            temp /= 7;
        }
        if (num < 0) ans.append("-");
        ans = ans.reverse();

        return ans.toString();
    }
}
