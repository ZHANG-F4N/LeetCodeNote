import java.util.Arrays;

public class a912sortArray {
    public static void main(String[] args) {
        int[] num = {5, 1, 1, 2, 0, 0};
        System.out.println(Arrays.toString(sortArray(num)));
    }

    public static int[] sortArray(int[] nums) {
        int N = nums.length;
        // 一组的长度
        int len = 1;
        int i = 0;
        while (len < N) {
            int[] tempArr = new int[N];
            while (i < N) {
                int lidx = i;
                int lend = i + len - 1;
                int ridx = i + len;
                int rend = i + len + len - 1;
                if (lend >= N) {
                    lend = N - 1;
                    while (lidx <= lend) {
                        tempArr[i++] = nums[lidx++];
                    }
                    break;
                }
                if (rend >= N) {
                    rend = N - 1;
                }
                while (lidx <= lend && ridx <= rend) {
                    if (nums[lidx] <= nums[ridx]) {
                        tempArr[i++] = nums[lidx++];
                    } else {
                        tempArr[i++] = nums[ridx++];
                    }
                }
                while (lidx <= lend) {
                    tempArr[i++] = nums[lidx++];
                }
                while (ridx <= rend) {
                    tempArr[i++] = nums[ridx++];
                }
            }
            len <<= 1;
            i = 0;
            nums = tempArr;
        }
        return nums;
    }
}
