import java.util.HashMap;

public class LeetCode0911a1 {
    public static void main(String[] args) {
//        int[][] source = {{1, 3}, {5, 4}};
//        int[][] target = {{3, 1}, {6, 5}};

        int[][] source = {{1, 2, 3}, {3, 4, 5}};
        int[][] target = {{1, 3, 5}, {2, 3, 4}};

        System.out.println(minimumSwitchingTimes(source, target));

    }

    public static int minimumSwitchingTimes(int[][] source, int[][] target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int N = source.length;
        int M = source[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                hashMap.put(source[i][j], hashMap.getOrDefault(source[i][j], 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int tar = target[i][j];
                if (hashMap.containsKey(tar)) {
                    hashMap.replace(tar, hashMap.get(tar) - 1);
                    if (hashMap.get(tar) == 0) {
                        hashMap.remove(tar);
                    }
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
}
