public class a1576modifyString {
    public static void main(String[] args) {
        System.out.println(modifyString("j?qg??b"));
    }

    public static String modifyString(String s) {
        if (s.length() == 1 && s.equals("?")) return "a";
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '?') continue;
            char l = ' ';
            char r = ' ';
            if (arr[i] == '?') {
                if (i - 1 >= 0) l = arr[i - 1];
                if (i + 1 < arr.length && arr[i + 1] != '?') r = arr[i + 1];
                if (l == ' ' && r == ' ') {
                    arr[i] = 'a';
                    continue;
                }
                if (l == ' ') l = r;
                else if (r == ' ') r = l;
                for (int j = 0; j < 26; j++) {
                    if ('a' + j != l && 'a' + j != r) {
                        arr[i] = (char) ('a' + j);
                        break;
                    }
                }
            }

        }
        return new String(arr);
    }
}
