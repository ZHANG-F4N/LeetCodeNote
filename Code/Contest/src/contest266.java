import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class contest266 {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(1, new int[]{1}));
        System.out.println(minimizedMaximum(7, new int[]{15, 10, 10}));
        System.out.println(minimizedMaximum(6, new int[]{11, 6}));
    }

    /*
     * 二分搜索
     * 假设每个店最大商品数是 x
     * 那么大于x的数也都可以满足条件。
     *
     * */
    public static int minimizedMaximum(int n, int[] quantities) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < quantities.length; i++) {
            right = right > quantities[i] ? right : quantities[i];
        }
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (judge(n, quantities, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 最多分配 x 满足条件返回 true 不满足条件返回 false
    public static boolean judge(int n, int[] quantities, int x) {
        if (x == 0) {
            return Arrays.stream(quantities).sum() == 0;
        }
        int need = 0;
        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] <= x) {
                need++;
            } else {
                need += ((quantities[i] - 1) / x) + 1;
            }
            if (need > n) {
                return false;
            }
        }
        return need <= n;
    }

    public static long countVowels(String word) {
        long ans = 0;
        char[] chars = word.toCharArray();
        int L = word.length();
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        //bba ba a
        //bbac bac ac
        //  b   b   a   c
        //  0   1   2   3
        //  0   0
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                ans += (long)(i+1) * (L - i);
            }
        }
        return ans;

    }
    public static int countVowelSubstrings(String word) {
        int N = word.length();
        int ans = 0;
        char[] arr = word.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
//        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        for (int i = 0; i < N; i++) {
            hashMap.put('a', 1);
            hashMap.put('e', 1);
            hashMap.put('i', 1);
            hashMap.put('o', 1);
            hashMap.put('u', 1);
            for (int j = i; j < N; j++) {
                char ch = arr[j];
                if (!set.contains(ch)) {
                    break;
                } else {
                    if (hashMap.get(ch) == 1) {
                        hashMap.put(ch, 0);
                    }
                }
                int num = hashMap.get('a') + hashMap.get('e') + hashMap.get('i') + hashMap.get('o') + hashMap.get('u');
                if (num == 0) {
                    ans++;
                }
            }
        }
        return ans;

    }

}
