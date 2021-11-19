public class a88minCostClimbingStairs {
    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int dp1 = 0;
        int dp2 = 0;
        int dp = 0;
        for (int i = 2; i < cost.length; i++) {
            dp = Math.min(dp1 + cost[i - 2], dp2 + cost[i-1]);
            dp1 = dp2;
            dp2 = dp;
        }
        dp = Math.min(dp1 + cost[cost.length - 2], dp2 + cost[cost.length-1]);
        return dp;
    }

}
