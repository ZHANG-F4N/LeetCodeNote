public class a1618patternMatching {
    public static void main(String[] args) {
        System.out.println(patternMatching("aaaa", "dogcatcatdog"));
        System.out.println(patternMatching("abba", "dogcatcatdog"));
        System.out.println(patternMatching("abba", "dogcatcatfish"));

    }

    public static boolean patternMatching(String pattern, String value) {
        String tempP = pattern;
        String tempV = value;
        int a = 0;
        int b = 0;
        for (char c : pattern.toCharArray()) {
            if (c == 'a') a++;
            else b++;
        }
        //if (a == 0) String otherStr = tempV.substring(0, tempV.length() / other);
        for (int i = 0; i < value.length(); i++) {
            tempP = pattern;
            tempV = value;
            String begStr = value.substring(0, i + 1);
            char ch = pattern.charAt(0);

            tempV =  tempV.replaceAll(begStr, "");
            int other = 0;
            char otherCh = 'a';
            if (ch == 'a') {
                otherCh = 'b';
                other = b;
            } else {
                otherCh = 'a';
                other = a;
            }
            String otherStr = tempV.substring(0, tempV.length() / other);
            tempP = tempP.replaceAll(String.valueOf(ch), begStr);
            tempP = tempP.replaceAll(String.valueOf(otherCh), otherStr);
            if (tempP.equals(value)) return true;
        }
        return false;
    }
}
