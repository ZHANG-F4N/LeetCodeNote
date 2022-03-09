public class a798bestRotation {
    public static void main(String[] args) {
        System.out.println(bestRotation(new int[]{2, 3, 1, 4, 0}));
    }

    public static int bestRotation(int[] nums) {
        // 0 <= nums[i] < nums.length
        int n = nums.length;
        // 下标为 i 的元素 进行 k 论调后的下标为 (i - k + n) % n


        //  points[k] 表示轮调下标为 k 时的得分
        // 用差分数组保存
        int[] points = new int[n];
        // 对于数组 nums 中的每个元素，得到该元素记 1 分的轮调下标范围，
        // 然后将数组 points 的该下标范围内的所有元素加 1。

        //假设元素 x 的初始下标为 i。
        // 当 i < x 时应将 points 的下标范围 [i+1,i−x+n] 内的所有元素加 1，
        // 当 i ≥ x 时应将 points 的下标范围 [0,i+1] 和 [i−x,n−1] 内的所有元素加 1。


        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            // +1 配合差分
            int high = (i - nums[i] + n + 1) % n;
            points[low]++;
            points[high]--;
            // ???
            if (low >= high) {
                points[0]++;
            }
        }
        // 算最高分
        int ans = 0;
        int max = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            if (points[i] + pre > max) {
                max = points[i] + pre;
                ans = i;
            }
            pre = points[i] + pre;
        }


        return ans;

    }
}
