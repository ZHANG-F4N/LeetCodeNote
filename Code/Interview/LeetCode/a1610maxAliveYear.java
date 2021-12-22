public class a1610maxAliveYear {
    public static void main(String[] args) {
        System.out.println(maxAliveYear(new int[]{1900, 1901, 1950}, new int[]{1948, 1951, 2000}));
    }

    public static int maxAliveYear(int[] birth, int[] death) {
        int[] numAlive = new int[101];
        int[] numDead = new int[101];
        int n = birth.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            numAlive[birth[i] - 1900]++;
            numDead[death[i] - 1900]++;
        }
        int ans = 1900;
        int preAlive = 0;
        int preDead = 0;
        for (int i = 0; i < 101; i++) {
            int num = preAlive + numAlive[i] - preDead;
            if (max < num) {
                max = num;
                ans = i + 1900;
            }
            preAlive += numAlive[i];
            preDead += numDead[i];

        }
        return ans;

        /*
         *  idx 0   1   2   3   4   5   6   7
         *  arr 0   2   5   4   9   7   10  0
         *  dx  0   2   3   -1  5   -2  3   -10
         *  --区间[3~5]都进行+1操作
         *  dx 只需要  在 i = 3 进行+1
         *  dx         在 i = 6 进行-1
         * */

    }
}
