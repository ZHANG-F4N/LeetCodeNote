import java.util.Arrays;

public class a0504findClosedNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findClosedNumbers(9)));
        System.out.println(Arrays.toString(findClosedNumbers(2147483647)));
        System.out.println(Arrays.toString(findClosedNumbers(2)));
    }

    public static int[] findClosedNumbers(int num) {
        // 最大 : 把所有 1 放到最前面
        // 最小 : 把所有 1 放到最后面
        // 略小 : 从右往左，找到第一个 10 位置，然后把 10 转为 01，
        //          右侧剩下的 1 移到右侧的 高位，右侧剩下的位置0。
        // 略大 : 从右往左，找到第一个 01 位置，然后把 01 转为 10，
        //        右侧剩下的 1 移到右侧的 低位，右侧剩下的位置0。
        int first10 = -8;
        first10 =~first10;
        int numOne = num & 1;
        for (int i = 1; i < 32; i++) {
            if ((num & (1 << i)) != 0 && (num & (1 << (i - 1))) == 0) {
                first10 = i;
                break;
            }
            if ((num & (1 << i)) != 0) {
                numOne++;
            }
        }
        int small = num;
        if (first10 == -1) {
            small = -1;
        } else {
            small = (num & ~(1 << first10)) | (1 << (first10 - 1));
            first10 -= 2;
            while (numOne != 0) {
                small = small | (1 << first10);
                numOne--;
                first10--;
            }
            while (first10 != -1) {
                small = small & ~(1 << first10);
                first10--;
            }
        }


        int first01 = -1;
        int numZero = ~num & 1;
        for (int i = 1; i < 32; i++) {
            if ((num & (1 << i)) == 0 && (num & (1 << (i - 1))) != 0) {
                first01 = i;
                break;
            }
            if ((num & (1 << i)) == 0) {
                numZero++;
            }
        }
        int bigger = -1;
        if (first01 == 31) {
            bigger = -1;
        } else {
            bigger = (num | (1 << first01)) & ~(1 << (first01 - 1));
            first01 -= 2;
            while (numZero != 0) {
                bigger = bigger & ~(1 << first01);
                numZero--;
                first01--;
            }
            while (first01 != -1) {
                bigger = bigger | (1 << first01);
                first01--;
            }
        }

        return new int[]{bigger, small};
    }
}
