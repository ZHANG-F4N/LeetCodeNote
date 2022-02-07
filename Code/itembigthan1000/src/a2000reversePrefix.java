public class a2000reversePrefix {
    public static void main(String[] args) {
        System.out.println(reversePrefix("abcdefd", 'd'));
    }

    public static String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1){
            return word;
        }
        StringBuilder sb = new StringBuilder(word.substring(0,index+1));

        return sb.reverse().toString()+word.substring(index+1);
    }
}
