public class contest65a1 {
    public static void main(String[] args) {
        System.out.println(checkAlmostEquivalent("cccddabba", "cccddabba"));


    }

    public static boolean checkAlmostEquivalent(String word1, String word2) {
        int[] num1 = new int[26];
        int[] num2 = new int[26];

        for (char c : word1.toCharArray()) {
            num1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            num2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (Math.abs(num1[i] - num2[i]) > 3) {
                return false;
            }
        }
        return true;


    }
}
