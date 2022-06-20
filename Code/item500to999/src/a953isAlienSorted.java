public class a953isAlienSorted {
    public static void main(String[] args) {
        System.out.println(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }

    public static boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        char[] chars = order.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map[chars[i] - 'a'] = i;
        }
        int n = words.length;
        for (int i = 1; i < n; i++) {
            int min = Math.min(words[i - 1].length(), words[i].length());
            int j = 0;
            while (j < min) {
                int f = words[i - 1].charAt(j) - 'a';
                int b = words[i].charAt(j) - 'a';
                if (map[f] > map[b]) {
                    return false;
                } else if (map[f] < map[b]) {
                    break;
                }
                j++;
            }
            if (j == min && words[i - 1].length() > words[i].length()) {
                return false;
            }
        }
        return true;
    }
}
