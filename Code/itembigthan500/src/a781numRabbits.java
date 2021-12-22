import java.util.*;

public class a781numRabbits {
    public static void main(String[] args) {

        System.out.println(numRabbits(new int[]{10, 10, 10}));
        System.out.println(numRabbits(new int[]{1, 0, 1, 0, 0}));
    }

    public static int numRabbits(int[] answers) {
        int ans = 0;
        if (answers.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            int num = answers[i] + 1;
            if (hashMap.containsKey(num)) {
                if (hashMap.get(num) >= num) {
                    ans += num;
                    hashMap.put(num, 1);
                } else {
                    hashMap.put(num, hashMap.get(num) + 1);
                }
            } else {
                hashMap.put(num, 1);
            }
        }
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            ans += en.getKey();
        }
        return ans;
    }
}
