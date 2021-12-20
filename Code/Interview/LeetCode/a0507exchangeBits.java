public class a0507exchangeBits {
    public static void main(String[] args) {

    }

    public static int exchangeBits(int num) {
        int t = 0b0101_0101_0101_0101_0101_0101_0101_0101;
//        int temp = num;
//        // 保留奇数
//        num &= t;
//        num <<= 1;
//
//        temp &= t << 1;
//        temp >>= 1;
//        num += temp;

        return ((num & t) << 1) + ((num & (t << 1)) >> 1);

    }
}
