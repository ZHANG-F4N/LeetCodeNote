public class a122maxProfit {
//  7	1	3	5	4	3	6	4
//      ↓	↑	↑	↓	↓	↑	↓
//    买入		卖出	   买入 卖出
//		    4		          3		    7

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {
        int ans = 0;
        if (prices.length == 1 || prices.length == 0){
            return 0;
        }
        //收集所有上坡,上坡就有利可图
        for (int i = 1; i < prices.length; i++) {
            if(prices[i-1]<prices[i]){
                ans+=prices[i]-prices[i-1];
            }
        }
        return ans;
    }

}
