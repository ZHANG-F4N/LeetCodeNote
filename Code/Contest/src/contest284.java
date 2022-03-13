import java.util.ArrayList;
import java.util.List;

public class contest284 {
    public static void main(String[] args) {
//        System.out.println(findKDistantIndices(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1));
        System.out.println(digArtifacts(2, new int[][]{{0, 0, 0, 0}, {0, 1, 1, 1}}, new int[][]{{0, 0},
                {0, 1}, {1, 1}}));
        System.out.println(digArtifacts(2, new int[][]{{0, 0, 0, 0}, {0, 1, 1, 1}}, new int[][]{{0, 0},
                {0, 1}}));
        System.out.println(maximumTop(new int[]{5, 2, 2, 4, 0, 6}, 4));
    }

    //Q1
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i - j >= 0 && nums[i - j] == key) {
                    list.add(i);
                    break;
                } else if (i + j < n && nums[i + j] == key) {
                    list.add(i);
                    break;
                }
            }
        }

        return list;
    }

    //Q2
    public static int[][] grid;

    public static int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int ans = 0;
        grid = new int[n][n];

        for (int i = 0; i < dig.length; i++) {
            grid[dig[i][0]][dig[i][1]] = 1;
        }

        for (int k = 0; k < artifacts.length; k++) {
            int[] temp = artifacts[k];
            int l = temp[1], r = temp[3], t = temp[0], d = temp[2];
            boolean flag = true;
            for (int i = l; i <= r; i++) {
                for (int j = t; j <= d; j++) {
                    if (grid[j][i] == 0) flag = false;
                }
            }
            if (flag) ans++;
        }
        return ans;
    }

    //Q3
    public static int maximumTop(int[] nums, int k) {
        int n = nums.length;
        int[] maxArr = new int[n + 1];
        maxArr[0] = -1;
//        int max = -1;
        for (int i = 1; i <= n; i++) {
            if (maxArr[i - 1] > nums[i - 1]) maxArr[i] = maxArr[i - 1];
            else maxArr[i] = nums[i - 1];

//            if (nums[i] > max) max = nums[i];
        }
        if (k > n) return maxArr[n];
        if (k == n) return Math.max(max, nums[n - 1]);
        max = -1;
        for (int i = 0; i < k; i++) {

        }


        return 0;
    }
}
