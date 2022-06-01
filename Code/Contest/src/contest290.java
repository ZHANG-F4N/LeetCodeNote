import java.util.*;

public class contest290 {
    public static void main(String[] args) {
//        System.out.println(intersection(new int[][]{{3, 1, 2, 4, 5}, {1, 2, 3, 4}, {3, 4, 5, 6}}));
//        System.out.println(countLatticePoints(new int[][]{{2, 2, 2}, {3, 4, 1}}));
//        System.out.println(countRectangles(new int[][]{{1, 2}, {2, 3}, {2, 5}}, new int[][]{{2, 1},
//                {1, 4}}));
//        System.out.println(fullBloomFlowers(new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}}, new int[]{2, 3,
//                7, 11
//        }));
//        System.out.println(fullBloomFlowers(new int[][]{{1, 10}, {3, 3}}, new int[]{3, 3, 2}));
        System.out.println(fullBloomFlowers(new int[][]{{19, 37}, {19, 38}, {19, 35}},
                new int[]{6, 7, 21, 1, 13, 37, 5, 37, 46, 43}));
    }

    // Q1
    public static List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                hashMap.put(nums[i][j], hashMap.getOrDefault(nums[i][j], 0) + 1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            if (en.getValue() == n) list.add(en.getKey());
        }
        Collections.sort(list);
        return list;
    }

    //Q2
    public static int countLatticePoints(int[][] circles) {
        HashSet<Integer> ans = new HashSet<>();
        int n = circles.length;

        for (int i = 0; i < n; i++) {
            int xl = circles[i][0] - circles[i][2];
            int xr = circles[i][0] + circles[i][2];
            int yl = circles[i][1] - circles[i][2];
            int yr = circles[i][1] + circles[i][2];

            for (int j = xl; j <= xr; j++) {
                for (int k = yl; k <= yr; k++) {
                    if (check(circles[i][0], circles[i][1], circles[i][2], j, k)) {
                        ans.add(j * 10000 + k);
                    }
                }
            }
        }
        return ans.size();
    }

    public static boolean check(int x, int y, int r, int px, int py) {
        int dis = (px - x) * (px - x) + (py - y) * (py - y);
        return dis <= r * r;
    }

    // Q3
    public static int[] countRectangles(int[][] rectangles, int[][] points) {
        return null;
    }

    public static boolean check(int x, int y, int px, int py) {
        return (px <= x && px >= 0) || (py <= y || py >= 0);
    }

    //Q3
    public static int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Arrays.sort(flowers, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        class node {
            int idx;
            int time;
        }
        ArrayList<node> nodes = new ArrayList<>();
        int n = persons.length;
        for (int i = 0; i < n; i++) {
            node node = new node();
            node.idx = i;
            node.time = persons[i];
            nodes.add(node);
        }
        int[] ans = new int[n];
        int j = 0;
        PriorityQueue<int[]> times = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        Collections.sort(nodes, Comparator.comparingInt(o -> o.time));
        for (int i = 0; i < n; i++) {
            int arr = nodes.get(i).time;
            int idx = nodes.get(i).idx;
            while (j < flowers.length && flowers[j][0] <= arr) {
                times.offer(flowers[j]);
                j++;
            }
            while (!times.isEmpty() && times.peek()[1] < arr) {
                times.poll();
            }
            ans[idx] = times.size();
        }

        return ans;


    }

}
