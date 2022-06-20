import java.util.Arrays;
import java.util.HashMap;

public class contest296 {
    public static void main(String[] args) {
//        minMaxGame(new int[]{70, 38, 21, 22});
//        partitionArray(new int[]{16, 8, 17, 0, 3, 17, 8, 20}, 10);
//        partitionArray(new int[]{3, 6, 1, 2, 5}, 2);
//        arrayChange(new int[]{1}, new int[][]{{1,2}, {2, 3}, {3,1000000},{1000000,1}});
        arrayChange(new int[]{1, 2}, new int[][]{{1, 3}, {2, 1}, {3, 2}});
        arrayChange(new int[]{1, 2, 4, 6}, new int[][]{{1, 3}, {4, 7}, {6, 1}});
    }

    //Q1
    public static int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        while (n != 1) {
            n = n >> 1;
            for (int i = 0; i < n; i++) {
                if ((i & 1) == 0) nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                else nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }
        return nums[0];
    }


    //Q2
    public static int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);

        int ans = 1;

        int n = nums.length;
        int min = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] - min > k) {
                min = nums[i];
                ans++;
            }
        }
        return ans;
    }

    //Q3
    public static int[] arrayChange(int[] nums, int[][] operations) {

        HashMap<Integer, Integer> hashMapK2Y = new HashMap<>();
        HashMap<Integer, Integer> hashMapY2K = new HashMap<>();
        for (int[] operation : operations) {
            if (hashMapY2K.containsKey(operation[0])) {

                hashMapK2Y.put(hashMapY2K.get(operation[0]), operation[1]);

                hashMapY2K.put(operation[1], hashMapY2K.get(operation[0]));
            } else {
                hashMapK2Y.put(operation[0], operation[1]);
                hashMapY2K.put(operation[1], operation[0]);

            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = hashMapK2Y.containsKey(nums[i]) ? hashMapK2Y.get(nums[i]) : nums[i];
        }


        return nums;

    }
}
