import java.util.Arrays;

public class contest275 {
    public static void main(String[] args) {
//        System.out.println(checkValid(new int[][]{{1, 2, 3}, {3, 1, 2}, {2, 3, 1}}));
//        System.out.println(minSwaps(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}));
//        System.out.println(minSwaps(new int[]{1}));
//        System.out.println(minSwaps(new int[]{0, 1, 0, 1, 1, 0, 0}));
//        System.out.println(minSwaps(new int[]{1, 1, 0, 0, 1}));
        test();
    }


    public static boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        boolean[] flag = new boolean[matrix.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flag[matrix[i][j] - 1] = true;
            }
            for (int j = 0; j < n; j++) {
                if (flag[j] == false) return false;
                flag[j] = false;
            }
            for (int j = 0; j < n; j++) {
                flag[matrix[j][i] - 1] = true;
            }
            for (int j = 0; j < n; j++) {
                if (flag[j] == false) return false;
                flag[j] = false;
            }
        }
        return true;

    }

    public static int minSwaps(int[] nums) {
        int n = nums.length;
        int win = Arrays.stream(nums).sum();
        int l = 0;
        int count = 0;
        int ans = win;
        for (int i = 0; i < win; i++) {
            if (nums[i] == 0) count++;
        }
        ans = Math.min(ans, count);
        while (l < n) {
            int idx = (l + win) % n;
            if (nums[idx] == 0) count++;
            if (nums[l] == 0) count--;
            ans = Math.min(ans, count);
            l++;
        }
        return ans;
    }

    public static void test() {
        String s = new String();
        for (int i = 0; i < 400000; i++) {
            s+='1';
        }
        String b = s;
        b.replace('1','2');
        System.out.println(s);
        System.out.println(b);
    }

}
