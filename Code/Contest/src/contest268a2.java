public class contest268a2 {
    public static void main(String[] args) {
        System.out.println(wateringPlants(new int[]{3, 2, 4, 2, 1}, 6));//17
        System.out.println(wateringPlants(new int[]{2, 2, 3, 3}, 5));
        System.out.println(wateringPlants(new int[]{1, 1, 1, 4, 2, 3}, 4));
        System.out.println(wateringPlants(new int[]{7, 7, 7, 7, 7, 7, 7}, 8));
    }

    /*
     *      3   2   4   2   1
     *  6   3   1               1+1     2
     *  6           2   0       2+3+1   6
     *  6                   5   4+5     9
     * */
    public static int wateringPlants(int[] plants, int capacity) {
        int ans = 1;
        int rest = capacity - plants[0];
        for (int i = 1; i < plants.length; i++) {
            if (rest < plants[i]) {
                ans += 2 * i;
                rest = capacity;
//                rest -= plants[i];
//                continue;
            }
            ans++;
            rest -= plants[i];
        }
        return ans;

    }
}
