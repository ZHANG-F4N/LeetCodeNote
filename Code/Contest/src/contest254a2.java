import java.util.Arrays;

public class contest254a2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(rearrangeArray(new int[]{10, 7, 5, 4, 3})));
        System.out.println(Arrays.toString(rearrangeArray(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(rearrangeArray(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(rearrangeArray(new int[]{6, 2, 0, 9, 7})));
    }

    public static int[] rearrangeArray(int[] nums) {
        // int[] val = new int[100001];
        int N = nums.length;
//        for (int i = 0; i < N; i++) {
//            val[nums[i]] = 1;
//        }
//        int idx = 0;
//        for (int i = 0; i < val.length; i++) {
//            if (val[i] == 1) {
//                nums[idx++] = i;
//            }
//        }

        boolean flag = false;
        while (!flag) {
            int l = 0;
            int m = 1;
            for (int r = 2; r < N; r++) {
                if (((float) nums[l] + nums[r]) / 2 == (float) nums[m]) {
                    int temp = nums[m];
                    nums[m] = nums[r];
                    nums[r] = temp;
                }
                l++;
                m++;
            }
            l = 0;
            m = 1;
            boolean ok = true;
            for (int r = 2; r < N; r++) {
                if (((float) nums[l] + nums[r]) / 2 == (float) nums[m]) {
                    ok = false;
                }
                l++;
                m++;
                if (r == N - 1 && ok) {
                    flag = true;
                }
            }
        }


        return nums;


    }
}
