import java.util.HashSet;

public class a824toGoatLatin {
    public static void main(String[] args) {
        System.out.println(toGoatLatin("I speak Goat Latin"));
    }

    public static String toGoatLatin(String sentence) {
        HashSet<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('e');
        characters.add('i');
        characters.add('o');
        characters.add('u');
        characters.add('A');
        characters.add('E');
        characters.add('I');
        characters.add('O');
        characters.add('U');

        String[] s = sentence.split(" ");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            StringBuilder temp = new StringBuilder(s[i]);

            if (!characters.contains(temp.charAt(0))) {
                char ch = temp.charAt(0);
                temp.deleteCharAt(0);
                temp.append(ch);
            }
            temp.append("ma");
            for (int j = 0; j < i + 1; j++) {
                temp.append("a");
            }
            if (i == s.length - 1) ans.append(temp);
            else ans.append(temp + " ");
        }
        return ans.toString();
    }
}
