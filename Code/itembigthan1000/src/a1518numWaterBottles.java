public class a1518numWaterBottles {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(30));
        System.out.println(numWaterBottles(9, 3));
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        int voidBottles = numBottles;
        while (voidBottles >= numExchange) {
            int change = voidBottles / numExchange;
            ans += change;
            voidBottles = change + voidBottles - change * numExchange;
        }
        return ans;
    }

    public static int trailingZeroes(int n) {
        int ans = 0;
        for (long num = 5; n / num > 0; num *= 5) {
            ans += n / num;
        }
        return ans;
    }
}
