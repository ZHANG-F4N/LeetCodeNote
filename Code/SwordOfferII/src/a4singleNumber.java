import java.util.ArrayList;

public class a4singleNumber {
    /*
     * 给你一个整数数组 nums ，
     * 除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
     * 请你找出并返回那个只出现了一次的元素。
     * */
    public static void main(String[] args) {
        int[] nums = {-2, -2, 1, 1, 4, 1, 4, 4, -4, -2};
        System.out.println(singleNumber2(nums));
    }

    public static int singleNumber(int[] nums) {

        int[] bits = new int[32];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 31; j >= 0; j--) {
                if ((nums[i] & 1) == 1) {
                    bits[j]++;
                }
                nums[i] = (nums[i] >> 1);
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            bits[i] %= 3;
            str.append(bits[i]);
        }
        System.out.println(str);
        //return Integer.parseInt(str.toString(), 2); 解析符号有问题

        return Integer.parseUnsignedInt(str.toString(), 2);
    }

    public static int singleNumber2(int[] nums) {

        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                cnt += (nums[j] >> i) & 1;
            }
            if (cnt % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }
}