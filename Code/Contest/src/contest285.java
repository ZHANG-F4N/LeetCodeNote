public class contest285 {
    public static void main(String[] args) {
//        System.out.println(countHillValley(new int[]{6,6,5,5,4,1}));
//        System.out.println(countHillValley(new int[]{2, 4, 1, 1, 6, 5}));
//        System.out.println(countCollisions("SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR"));
//        System.out.println(countCollisions("RLRSLL"));
//        System.out.println(countCollisions("LLRR"));
        System.out.println(maximumBobPoints(9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0}));
        System.out.println(maximumBobPoints(3, new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2}));
    }

    // Q1
    public static int countHillValley(int[] nums) {

        int n = nums.length;


        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;
        right[n - 1] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) left[i] = 1;
            else if (nums[i - 1] > nums[i]) left[i] = -1;
            else left[i] = 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) right[i] = 1;
            else if (nums[i] < nums[i + 1]) right[i] = -1;
            else right[i] = 0;
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (left[i] == -1 && right[i] == -1) ans++;
            if (left[i] == 1 && right[i] == 1) ans++;
            if (left[i] == 1 && right[i] == 0) {
                while (i < n - 1 && right[i] == 0) i++;
                if (right[i] == 1) ans++;
            }
            if (left[i] == -1 && right[i] == 0) {
                while (i < n - 1 && right[i] == 0) i++;
                if (right[i] == -1) ans++;
            }
        }
        return ans;
    }

    //Q2
    public static int countCollisions(String directions) {
        char[] dir = directions.toCharArray();
        int n = dir.length;
        if (n == 1) return 0;
        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
            if (dir[i] == 'R' && dir[i + 1] == 'L') {
                ans += 2;
                dir[i] = 'S';
                dir[i + 1] = 'S';
                continue;
            }
            if (dir[i] == 'R' && dir[i + 1] == 'S') {
                ans++;
                dir[i] = 'S';
                continue;
            }
            if (dir[i] == 'S' && dir[i + 1] == 'L') {
                ans++;
                dir[i + 1] = 'S';
                continue;
            }
        }
        for (int i = n - 1; i > 0; i--) {

            if (dir[i] == 'S' && dir[i - 1] == 'R') {
                ans++;
                dir[i - 1] = 'S';
            }
        }
        return ans;
    }


    private static int[] bob;
    private static int max;
    private static int[] ans;

    //Q3
    public static int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        bob = new int[12];
        ans = new int[12];
        max = 0;
        backtrace(numArrows, aliceArrows, 0, 0);
        return ans;
    }

    public static void backtrace(int numArrows, int[] aliceArrows, int i, int score) {
        if (numArrows < 0) return;
        if (i == 12) {
            if (score > max) {
                max = score;
                for (int j = 0; j < 12; j++) {
                    ans[j] = bob[j];
                }
                if (numArrows != 0) {
                    ans[11] += numArrows;
                }
            }
            return;
        }


        bob[i] = aliceArrows[i] + 1;
        backtrace(numArrows - aliceArrows[i] - 1, aliceArrows, i + 1, score + i);
        bob[i] = 0;
        backtrace(numArrows, aliceArrows, i + 1, score);
    }
}
