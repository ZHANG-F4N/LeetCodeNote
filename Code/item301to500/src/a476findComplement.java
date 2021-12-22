public class a476findComplement {
    public static void main(String[] args) {
        System.out.println(findComplement(10));
    }

    public static int findComplement(int num) {


        int index = 0;
        int temp = 1 << 31;
        for (int i = 0; i < 32; i++) {
            if ((temp & num) >= 1) {
                index = i;
                break;
            }
            temp >>= 1;
        }
        index = 32 - index;
        //掩码
        temp = index == 31 ? 0x7fffffff : (1 << index ) - 1;
        return num ^ temp;
    }
}
