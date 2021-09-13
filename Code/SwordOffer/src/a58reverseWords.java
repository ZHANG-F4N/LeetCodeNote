public class a58reverseWords {
    public static void main(String[] args) {
        String str = "  hello world!  ";
        System.out.println(reverseWords(str));
    }

    public static String reverseWords(String s) {

        if (s.equals("")) {
            return s;
        }
        s = s.trim();
        String[] words = s.split("\\ ");
        StringBuilder ans = new StringBuilder();
        for (int length = words.length - 1; length >= 0; length--) {
            if (words[length].equals("")) {
                continue;
            }
            ans.append(words[length]);
            if (length != 0){
                ans.append(" ");
            }
        }
        return ans.toString();
    }
}
