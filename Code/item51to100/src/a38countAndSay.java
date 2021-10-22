public class a38countAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String temp = countAndSay(n - 1);
        StringBuilder ret = new StringBuilder();
        int N = temp.length();
        char ch = temp.charAt(0);
        int sum = 0;
        int index = 0;
        for (int i = 1; i < N; i++) {
            if (ch != temp.charAt(i)) {
                sum = i - index;
                index = i;
                ret.append(sum);
                ret.append(ch);
                ch = temp.charAt(i);
            }
        }
        ret.append(N - index);
        ret.append(temp.charAt(index));
        return ret.toString();
    }
}
