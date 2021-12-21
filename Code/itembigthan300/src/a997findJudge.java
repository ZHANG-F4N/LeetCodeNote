public class a997findJudge {
    public static void main(String[] args) {
        System.out.println(findJudge(2, new int[][]{{1, 2}}));
        System.out.println(findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));
    }

    public static int findJudge(int n, int[][] trust) {

        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        for (int i = 0; i < trust.length; i++) {
            in[trust[i][1]]++;
            out[trust[i][0]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (in[i] == n - 1 && out[i] == 0) return i;
        }
        return -1;

    }
}
