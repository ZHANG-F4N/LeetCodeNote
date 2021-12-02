import java.util.HashMap;

public class a0102CheckPermutation {
    public static void main(String[] args) {
        System.out.println(CheckPermutation("abc", "bca"));
        System.out.println(CheckPermutation("abc", "bad"));
    }

    public static boolean CheckPermutation(String s1, String s2) {
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (char c : s1.toCharArray()) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (hashMap.getOrDefault(c, -1) > 0) {
                hashMap.put(c, hashMap.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;


    }
}
