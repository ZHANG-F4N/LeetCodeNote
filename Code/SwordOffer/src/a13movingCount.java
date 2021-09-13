import java.util.Arrays;
import java.util.HashMap;

public class a13movingCount {
    public static void main(String[] args) {
        System.out.println(movingCount(16, 8, 4));

    }

    static int ans = 0;

    public static int movingCount(int m, int n, int k) {
        ans = 0;
        boolean[][] visit = new boolean[m][n];
        trackBack(m, n, visit, 0, 0, k);
        return ans;
    }

    public static void trackBack(int row, int col, boolean[][] visit, int i, int j, int k) {

        if (i < 0 || i >= row || j < 0 || j >= col || visit[i][j]) {
            return;
        }
        int sum = 0;
        int temp = i;
        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }
        temp = j;
        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }
        if (sum <= k) {
            ans++;
        }else {
            return;
        }
        visit[i][j] = true;
        trackBack(row, col, visit, i + 1, j, k);
        //trackBack(row, col, visit, i - 1, j, k);
        trackBack(row, col, visit, i, j + 1, k);
        //trackBack(row, col, visit, i, j - 1, k);
        //visit[i][j] = false;
    }
}
