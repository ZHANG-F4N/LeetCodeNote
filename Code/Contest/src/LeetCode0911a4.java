public class LeetCode0911a4 {
    public static void main(String[] args) {
        int[][] toys = {{1, 0, 1}, {2, 0, 1}, {2, 0, 2}};
        int[][] circles = {{2, 0}};
        int r = 1;
        System.out.println(circleGame(toys, circles, r));
    }

    public static int circleGame(int[][] toys, int[][] circles, int r) {
        int ans = 0;

        int N = toys.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < circles.length; j++) {
                if (judge(toys[i][0], toys[i][1], toys[i][2], circles[j][0], circles[j][1], r)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static boolean judge(int x1, int y1, int r1, int x2, int y2, int r2) {
        if (r1 > r2) {
            return false;
        }
        long dis = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        if (dis >= (r1 + r2) * (r1 + r2)) {
            return false;
        }
        long rdis = Math.abs(r1 - r2);
        rdis = rdis * rdis;
        if (dis <= rdis) {
            return true;
        }
        return false;
    }
}
