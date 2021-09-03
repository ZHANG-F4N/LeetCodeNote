public class a58reverseLeftWords {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        System.out.println(reverseLeftWords(s, k));
    }

    public static String reverseLeftWords(String s, int n) {

        StringBuilder ans = new StringBuilder();
        String temp1 = s.substring(0, n);
        String temp2 = s.substring(n, s.length());

        ans.append(temp2);
        ans.append(temp1);

        return ans.toString();
    }
}
