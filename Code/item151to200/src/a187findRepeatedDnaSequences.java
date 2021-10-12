import java.util.*;

public class a187findRepeatedDnaSequences {
    public static void main(String[] args) {
        List<String> ans = findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");

        Iterator<String> it = ans.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    // hash
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (s.length() < 11) {
            return ans;
        }


        for (int i = 9; i < s.length(); i++) {
            String subStr = s.substring(i - 9, i + 1);
            hashMap.put(subStr, hashMap.getOrDefault(subStr, 0) + 1);
            if (hashMap.get(subStr) == 2) {
                ans.add(subStr);
            }
        }

        return ans;
    }

    // binary bit

    public static List<String> findRepeatedDnaSequences2(String s) {
        List<String> ans = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>() {
            {
                put('A', 0);
                put('C', 1);
                put('G', 2);
                put('T', 3);
            }
        };

        if (s.length() < 11) {
            return ans;
        }

//        int x = 0;
//        for (int i = 0; i < L - 1; ++i) {
//            x = (x << 2) | bin.get(s.charAt(i));
//        }
//        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
//        for (int i = 0; i <= n - L; ++i) {
//            x = ((x << 2) | bin.get(s.charAt(i + L - 1))) & ((1 << (L * 2)) - 1);
//            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
//            if (cnt.get(x) == 2) {
//                ans.add(s.substring(i, i + L));
//            }
//        }

        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp = (temp << 2) | map.get(s.charAt(i));
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            temp = (((temp << 2) | map.get(s.charAt(i + 9))) & ((1 << 20) - 1));
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
            if (hashMap.get(temp) == 2) {
                ans.add(s.substring(i, i + 10));
            }
        }
        return ans;
    }
}
