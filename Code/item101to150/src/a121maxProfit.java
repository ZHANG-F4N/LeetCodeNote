public class a121maxProfit {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int ans = 0;
        //  7	1	5	3	6	4

        int minPrice=Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i]<minPrice){
                minPrice = prices[i];
            }
            else if(prices[i]-minPrice >ans){
                ans = prices[i]-minPrice;
            }
        }
        return ans;
    }
}
