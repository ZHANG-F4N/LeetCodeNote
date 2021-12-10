import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class contest65 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6})));
    }

    public static int[] maximumBeauty(int[][] items, int[] queries) {
        HashMap<Integer, Integer> treeMap = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            if (treeMap.containsKey(items[i][0])) {
                int max = treeMap.get(items[i][0]) > items[i][1] ? treeMap.get(items[i][0]) : items[i][1];
                treeMap.put(items[i][0], max);
            } else {
                treeMap.put(items[i][0], items[i][1]);
            }
        }
        int num = treeMap.size();
        int[] max = new int[num];
        int[][] maxVal = new int[num][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            max[idx] = entry.getKey();
            maxVal[idx][1] = entry.getValue();
            maxVal[idx][0] = entry.getKey();
            idx++;
        }
        Arrays.sort(maxVal, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(max);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            idx = Arrays.binarySearch(max, queries[i]);
            if (idx < 0) {
                idx = -idx -2;
            }
            ans[i] = maxVal[idx][1];
        }
        return ans;
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
    static class Robot {
        //public int[][] map = new int[201][201];
        //HashMap<Character, Character> hashMap;
        int width;
        int height;
        char dir;
        int posX;
        int posY;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            posX = 0;
            posY = 0;
            dir = 'E';
//            hashMap = new HashMap<>();
//            hashMap.put('E', 'N');
//            hashMap.put('N', 'W');
//            hashMap.put('W', 'S');
//            hashMap.put('S', 'E');
        }

        public void move(int num) {

            // 计算一圈多少格
            int loop = 2 * (width + height) - 4;
            while (num != 0) {
                if (posX == 0 && posY == 0) {
                    num = num % loop;
                    if (num == 0) {
                        dir = 'S';
                    } else {
                        dir = 'E';
                    }
                }
                // 0,0 num = 5
                if (dir == 'E') {
                    int dis = width - posX - 1;
                    if (dis >= num) {
                        posX = posX + num;
                        num = 0;
                    } else {
                        posX = width - 1;
                        dir = 'N';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'N') {
                    int dis = height - posY - 1;
                    if (dis >= num) {
                        posY = posY + num;
                        num = 0;
                    } else {
                        posY = height - 1;
                        dir = 'W';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'W') {
                    int dis = posX;
                    if (dis >= num) {
                        posX = posX - num;
                        num = 0;
                    } else {
                        posX = 0;
                        dir = 'S';
                        num = num - dis;
                        continue;
                    }
                }
                if (dir == 'S') {
                    int dis = posY;
                    if (dis >= num) {
                        posY = posY - num;
                        num = 0;
                    } else {
                        posY = 0;
                        dir = 'E';
                        num = num - dis;
                        continue;
                    }
                }
            }
        }

        public int[] getPos() {
            return new int[]{posX, posY};
        }

        public String getDir() {
            if (dir == 'E') {
                return "East";
            }
            if (dir == 'W') {
                return "West";
            }
            if (dir == 'N') {
                return "North";
            }
            return "South";
        }
    }
}