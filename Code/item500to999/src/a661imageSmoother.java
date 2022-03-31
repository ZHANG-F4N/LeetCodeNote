public class a661imageSmoother {
    public static void main(String[] args) {
        imageSmoother(new int[][]{{100, 200, 100}, {200, 50, 200}, {100, 200, 100}});
    }

    public static int[][] imageSmoother(int[][] img) {

        int n = img.length;
        int m = img[0].length;
        int[][] ans = new int[n][m];
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = 0;
                int num = 0;
                for (int[] ints : dir) {
                    int x = i + ints[0];
                    int y = j + ints[1];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        val += img[x][y];
                        num++;
                    }
                }
                ans[i][j] = val / num;
            }
        }


        return ans;
    }
}
