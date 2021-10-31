import java.util.Arrays;

public class a260singleNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(singleNumber(new int[]{-1, 0})));

    }

    public static int[] singleNumber(int[] nums) {
//        int N = nums.length;
        int XOR = 0;
        for (int num : nums) {
            XOR ^= num;
        }
        int lowBit = XOR & (-XOR);

        // 一组lowbit位为1
        // 另外一组为0

        // 移32位移除
        // 防止溢出
        int temp = (XOR == Integer.MIN_VALUE ? XOR : XOR & (-XOR));
        //int temp = 1 << (lowBit - 1);
        int num1 = 0;
        int num2 = 0;
        for (int num : nums) {
            if ((num & temp) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[]{num1, num2};
    }
}
