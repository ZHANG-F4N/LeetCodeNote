import java.util.HashMap;
import java.util.Map;

public class LeetCode1024a2 {
    public static void main(String[] args) {
        System.out.println(nextBeautifulNumber(1));
    }

    public static int nextBeautifulNumber(int n) {
        int temp = n + 1;
        while (!judge(temp)) {
            temp++;
        }
        return temp;
    }

    public static boolean judge(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        while (n / 10 != 0) {
            int temp = n % 10;
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
            n /= 10;
        }
        if (n != 0) {
            int temp = n % 10;
            hashMap.put(temp, hashMap.getOrDefault(temp, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (en.getKey() != en.getValue()) {
                return false;
            }
        }
        return true;
    }
}
