public class a1252oddCells {
    public static void main(String[] args) {
        System.out.println(oddCells(2, 3, new int[][]{{0, 1}, {1, 1}}));
    }

    public static int oddCells(int m, int n, int[][] indices) {
        int[] col = new int[n];
        int[] row = new int[m];

        for (int i = 0; i < indices.length; i++) {
            row[indices[i][0]]++;
            col[indices[i][1]]++;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((row[i] + col[j]) % 2 == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
