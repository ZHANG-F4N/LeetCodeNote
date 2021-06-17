import java.util.*;

public class a621leastInterval {
    public static void main(String[] args) {
        char tasks[] = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }

    public static int leastInterval(char[] tasks, int n) {
        int ans = 0;
        int endTask = tasks.length;
        LinkedHashMap<Character, Integer> hashMap = new LinkedHashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            hashMap.put(tasks[i], hashMap.getOrDefault(tasks[i], 0) + 1);
        }
        ArrayList<Character> list = new ArrayList<>(hashMap.keySet());
        while (endTask > 0) {
            int temp = 0;
            int i = 0;
            for (; i < list.size() && temp <= n; i++) {
                if (hashMap.get(list.get(i)) != 0) {
                    hashMap.put(list.get(i), hashMap.get(list.get(i)) - 1);
                    ans++;
                    endTask--;
                    temp++;
                }
            }
            if (i == list.size() && temp <= n) {
                ans++;
                temp++;
            }
        }
        return ans;
    }
}
