import java.util.HashMap;
import java.util.Scanner;

public class HuaWei623 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String target = scanner.nextLine();
        String text = scanner.nextLine();
        text = text.toLowerCase();
        System.out.println(target + "  " + text);
        char[] testArray = text.toCharArray();
        StringBuffer tempStr = new StringBuffer("");
        HashMap<StringBuffer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < testArray.length; i++) {
            if (testArray[i] == ' ' || testArray[i] == ',' || testArray[i] == '.') {
                if (target.equals(tempStr)){.

                }

                tempStr = new StringBuffer("");
            } else {
                tempStr.append(testArray[i]);
            }
        }


        scanner.close();
    }

}
