public class a0501insertBits {
    public static void main(String[] args) {
        System.out.println(insertBits(1024, 19, 2, 6));
        System.out.println(insertBits(0, 31, 0, 4));
    }

    public static int insertBits(int N, int M, int i, int j) {
        M <<= i;
        for (; i <= j; i++) {
            N = (N & ~(1 << i)) | ((M & (1 << i)));
        }
        return N;
    }
}
