public class a868binaryGap {
    public static void main(String[] args) {

        System.out.println(binaryGap(5));
    }

    public static int binaryGap(int n) {

        int ans = 0;
        int i = 0;
        int index = -1;
        while ((n | 0) != 0) {
            if (index != -1 && (n & 1) == 1) {
                ans = ans > i - index ? ans : i - index;
                index = i;
            }
            if (index == -1 &&(n & 1) == 1) {
                index = i;
            }
            i++;
            n = n >> 1;
        }
        return ans;

    }
}
