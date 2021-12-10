import java.util.HashMap;
import java.util.Map;

public class contest66 {
    public static void main(String[] args) {
        System.out.println();
    }

    public static int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) {
            return 0;
        }
        int ans = 0;

        int i = startPos[0];
        int delta = 0;
        if (startPos[0] > homePos[0]) {
            delta = -1;
        }
        if (startPos[0] < homePos[0]) {
            delta = +1;
        }
        while (i != homePos[0] && delta != 0) {
            i += delta;
            ans += rowCosts[i];
        }
        int j = startPos[1];
        delta = 0;
        if (startPos[1] > homePos[1]) {
            delta = -1;
        }
        if (startPos[1] < homePos[1]) {
            delta = +1;
        }
        while (j != homePos[1] && delta != 0) {
            j += delta;
            ans += colCosts[j];
        }
        return ans;
    }
    public static int minimumBuckets(String street) {
        // 判断 -1
        if (street.length() == 1 && street.equals("H")) {
            return -1;
        }

        int ans = 0;

        char[] house = street.toCharArray();
        for (int i = 0; i < house.length; i++) {
            if (house[i] == '.' || house[i] == 'T') {
                continue;
            }
            if (i == 0) {
                if (house[i + 1] == 'H') {
                    return -1;
                }
                house[i + 1] = 'T';
            } else if (i == house.length - 1) {
                if (house[i - 1] == 'H') {
                    return -1;
                }
                house[i - 1] = 'T';
            } else {
                // 可能要约束下标
                if (house[i - 1] == 'H' && house[i + 1] == 'H') {
                    return -1;
                }
                if (house[i - 1] == 'T' || house[i + 1] == 'T') {
                    continue;
                }
                if (house[i - 1] == '.' && house[i + 1] == '.') {
                    house[i + 1] = 'T';
                } else if (house[i - 1] == '.') {
                    house[i - 1] = 'T';

                } else {
                    house[i + 1] = 'T';
                }
            }
        }
        for (char c : house) {
            if (c == 'T') {
                ans++;
            }
        }
        return ans;
    }
    public static int countWords(String[] words1, String[] words2) {
        HashMap<String, Integer> hashMap1 = new HashMap<>();
        HashMap<String, Integer> hashMap2 = new HashMap<>();
        for (String s : words1) {
            hashMap1.put(s, hashMap1.getOrDefault(s, 0) + 1);
        }
        for (String s : words2) {
            hashMap2.put(s, hashMap2.getOrDefault(s, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<String, Integer> en : hashMap1.entrySet()) {
            if (en.getValue() == 1 && hashMap2.getOrDefault(en.getKey(), 0) == 1) {
                ans++;
            }
        }
        return ans;
    }
    public static int countPyramids(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        int temp = ans;
        // 暴力超时
        for (int h = 2; h <= n; h++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    // grid[i][j]   当顶
                    //r <= i <= r + h - 1
                    boolean flag = true;
                    for (int i1 = i; i1 <= i + h - 1; i1++) {
                        //c - (i - r) <= j <= c + (i - r)
                        for (int j1 = j - (i1 - i); j1 <= j + (i1 - i); j1++) {
                            if (i1 < 0 || i1 >= n || j1 < 0 || j1 >= m || grid[i1][j1] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        ans++;
                    }
                }
            }
            if (temp == ans) {
                break;
            }
            temp = ans;
        }
        temp = ans;
        for (int h = 2; h <= n; h++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    // grid[i][j]   当顶
                    boolean flag = true;
                    //r - h + 1 <= i <= r
                    for (int i1 = i - h + 1; i1 <= i; i1++) {
                        //c - (r - i) <= j <= c + (r - i)
                        for (int j1 = j - (i - i1); j1 <= j + (i - i1); j1++) {
                            if (i1 < 0 || i1 >= n || j1 < 0 || j1 >= m || grid[i1][j1] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (!flag) {
                            break;
                        }
                    }
                    if (flag) {
                        ans++;
                    }
                }
            }
            if (temp == ans) {
                break;
            }
            temp = ans;
        }

        return ans;


    }

}
