public class a0103replaceSpaces {
    public static void main(String[] args) {
        System.out.println(replaceSpaces("Mr John Smith    ", 13));
        System.out.println(replaceSpaces("ds sdfs afs sdfa dfssf asdf             "
                , 27));

        System.out.println(replaceSpaces("               ", 5));
    }

    public static String replaceSpaces(String S, int length) {
        char[] str = S.toCharArray();
        int i = length - 1;
        int j = S.length() - 1;
        while (i >= 0) {
            if (str[i] == ' ') {
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
            } else {
                str[j--] = str[i];
            }
            i--;
        }
        return String.valueOf(str, j + 1, S.length() - j - 1);

    }

    public static String compressString(String S) {
        if (S.length() == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        char[] arr = S.toCharArray();
        int num = 1;
        char pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == pre) {
                num++;
            } else {
                ans.append(pre);
                ans.append(num);
                pre = ch;
                num = 1;
            }
        }
        ans.append(pre);
        ans.append(num);
        if (ans.length() >= S.length()) {
            return S;
        }
        return ans.toString();

    }
}
