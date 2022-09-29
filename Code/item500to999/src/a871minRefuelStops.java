import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class a871minRefuelStops {
    public static void main(String[] args) {
//        Q1();
        System.out.println(minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int ans = 0;
        int loc = 0, i = 0, n = stations.length, rem = startFuel;
        /*
        题目思路：
            - 将路上的一个个加油站 视为 一桶桶的油，每次经过的时候，就把油带上放后备箱；
            - 当油不够的时候，取出后备箱所带的 最多的那桶油 加进油箱
            - 这样以来，如若油箱和后备箱的油加起来都不够，那么就到不了了
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        while (loc < target) {
            if (rem == 0) {
                if (queue.isEmpty()) return -1;
                rem += queue.poll();
                ans++;
            } else {
                loc += rem;
                rem = 0;
                while (i < n && stations[i][0] <= loc) {
                    queue.offer(stations[i][1]);
                    i++;
                }
            }
        }
        return ans;

    }


    public static void Q1() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][2];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] split = s.split(",");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        if (n == 1) {
            System.out.println(arr[0][1] - arr[0][0]);
            return;
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int[] temp = arr[i];
            for (int j = i + 1; j < n; j++) {
                int[] next = arr[j];
                if (temp[1] > next[0]) {
                    if (temp[1] > next[1]) {
                        res.add(next);
                    } else if (temp[1] <= next[1]) {
                        res.add(new int[]{next[0], temp[1]});
                    }
                } else break;
            }
        }
        int all = 0;
        for (int i = 0; i < n; i++) {
            all += arr[i][1] - arr[i][0];
        }
        int over = 0;
        for (int i = 0; i < res.size(); i++) {
            over += res.get(i)[1] - res.get(i)[0];
        }
        System.out.println(all - over * 2);
    }
}
