public interface a1217minCostToMoveChips {

    public static void main(String[] args) {
        System.out.println(minCostToMoveChips(new int[]{2, 2}));
        System.out.println(minCostToMoveChips(new int[]{1, 2, 2, 2, 2}));
        System.out.println(minCostToMoveChips(new int[]{2, 2, 2, 3, 3}));
        System.out.println(minCostToMoveChips(new int[]{1, 2, 3}));
    }

    public static int minCostToMoveChips(int[] position) {
        int n = position.length;
        int odd = 0, even = 0;
        for (int i = 0; i < n; i++) {
            if (position[i] % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }
}
