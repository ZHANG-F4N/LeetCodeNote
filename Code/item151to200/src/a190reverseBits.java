public class a190reverseBits {
    public static void main(String[] args) {
        System.out.println(reverseBits(2));
    }

    public static int reverseBits(int n) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            ans |= (n & 1 )<<(31-i);
            n >>= 1;
        }
        return ans;
    }
}
