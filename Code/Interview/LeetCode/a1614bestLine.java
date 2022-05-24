import java.util.Arrays;

public class a1614bestLine {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(bestLine(new int[][]{{0, 0}, {1, 1}, {1, 0}, {2, 0}})));
    }

    public static int[] bestLine(int[][] points) {
        int n = points.length;
        int[] ans = new int[2];
        int max = 2;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int cnt = 2;
                long x1 = points[i][0] - points[j][0];
                long y1 = points[i][1] - points[j][1];

                for (int k = 0; k < n; k++) {
                    long x2 = points[i][0] - points[k][0];
                    long y2 = points[i][1] - points[k][1];

                    if (x1 * y2 == x2 * y1) cnt++;
                }
                if (cnt > max) {
                    max = cnt;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;

    }
}
