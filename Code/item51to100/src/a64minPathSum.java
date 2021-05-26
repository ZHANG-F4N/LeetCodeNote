public class a64minPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1},{2},{3}};
        System.out.println(minPathSum(grid));
    }

    public static int minPathSum(int[][] grid) {
//        int ans = 0;
//        if (grid.length == 1) {
//            for (int i = 0; i < grid[0].length; i++) {
//                ans += grid[0][i];
//            }
//            return ans;
//        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j]=Math.min(grid[i-1][j]+grid[i][j],grid[i][j-1]+grid[i][j]);
            }
        }
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                System.out.print(grid[i][j]);
//            }
//            System.out.println();
//        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
