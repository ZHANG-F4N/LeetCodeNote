public class a05replaceSpace {
    public static void main(String[] args) {
        String s = "We are happy.";
         s = "   ";

        System.out.println(replaceSpace(s));


    }

    public static String replaceSpace(String s) {

        StringBuilder ans = new StringBuilder();

        int i = 0, j = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                ans.append(s.substring(j, i) + "%20");
                j = i + 1;
            }
        }
        ans.append(s.substring(j, i));

//        String[] words = s.split(" ");
//
//
//        for (int i = 0; i < words.length - 1; i++) {
//            ans.append(words[i] + "%20");
//        }
//        ans.append(words[words.length - 1]);

        return ans.toString();
    }
}
