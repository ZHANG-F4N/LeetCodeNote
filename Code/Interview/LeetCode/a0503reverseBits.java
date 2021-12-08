public class a0503reverseBits {
    public static void main(String[] args) {
        System.out.println(reverseBits(45725232));
        System.out.println(reverseBits(2147483647));
        System.out.println(reverseBits(1775));
        System.out.println(reverseBits(7));
    }

    public static int reverseBits(int num) {
        int max = 0;
        int partLen = 0;
        int beg = 0;
        boolean flag = false;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                partLen++;
            } else {
                if (flag) {
                    partLen = 0;
                    flag = false;
                    i = beg;
                    // i
                } else {
                    flag = true;
                    partLen++;
                    beg = i;
                }
            }
            max = partLen > max ? partLen : max;
        }
        return max;


    }
}
