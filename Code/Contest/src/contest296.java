import java.util.ArrayList;

public class contest296 {
    public static void main(String[] args) {
//        minMaxGame(new int[]{70, 38, 21, 22});
        partitionArray(new int[]{16, 8, 17, 0, 3, 17, 8, 20}, 10);
        partitionArray(new int[]{3, 6, 1, 2, 5}, 2);
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
//        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        // {max,min}
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i];
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                int[] val = list.get(j);
                if (Math.abs(val[0] - key) <= k && Math.abs(val[1] - key) <= k) {
                    flag = true;
                    if (key > val[0]) val[0] = key;
                    if (key < val[1]) val[1] = key;
                    break;
                }
            }
            if (!flag) {
                list.add(new int[]{key, key});
                ans++;
            }
        }


        return list.size();
    }

    //Q3
    public static int[] arrayChange(int[] nums, int[][] operations) {
        return null;

    }
}
