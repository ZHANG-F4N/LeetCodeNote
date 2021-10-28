import java.util.Stack;

public class a394 {
    public static void main(String[] args) {
        System.out.println(decodeString("3[ab2[cd]ef]"));
        System.out.println(decodeString("2[abc]3[cd]ef)"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("abc3[cd]xyz"));
    }

    public static String decodeString(String s) {

        StringBuilder ans = new StringBuilder();
        Stack<String> code = new Stack<>();
        Stack<Integer> num = new Stack<>();
        int left = 0;
        //int right = 0;
        int idx = 0;
        // 3[ab2[cd]ef]
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            if (Character.isDigit(ch)) {
                num.push(ch - '0');
                idx++;
                continue;
            }
            if (ch == '[') {
                code.push(s.substring(left, idx));
                idx++;
                left = idx;
                continue;
            }
            if (ch == ']') {
                int rep = num.pop();
                String tempStr = code.pop();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < rep; i++) {
                    temp.append(tempStr);
                }
                code.push(temp.toString());
                idx++;
            }
        }


        return ans.toString();
    }
}
