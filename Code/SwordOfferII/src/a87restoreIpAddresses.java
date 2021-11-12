import java.util.ArrayList;
import java.util.List;

public class a87restoreIpAddresses {
    public static void main(String[] args) {
        restoreIpAddresses("25525511135");
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        String[] list = new String[4];


        return ans;
    }

    public static void traceback(List<String> ans, String[] list, StringBuilder ip, int idx,
                                 int finish) {
        if (finish == 4 && idx >= ip.length()) {
            return;
        }





    }

    public static boolean judge(String str) {
        if (str.length() == 0) {
            return false;
        }
        if (str.length() == 1) {
            return true;
        }
        if (str.charAt(0) == '0') {
            return false;
        }
        int val = Integer.parseInt(str);
        if (val > 255) {
            return false;
        }
        return true;
    }

}
