public class a231isPowerOfTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(5));
        System.out.println(isPowerOfTwo(31));
        System.out.println(isPowerOfTwo(32));
    }

    public static boolean isPowerOfTwo(int n) {

        // n > 0 是排除0和溢出 溢出为负值.
        return n > 0 && (n & -n) == n;
    }
}
