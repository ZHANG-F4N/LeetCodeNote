public class a0506convertInteger {
    public static void main(String[] args) {
        System.out.println(convertInteger(29, 15));
    }

    public static int convertInteger(int A, int B) {
        int ans = 0;
        A = A ^ B;
        for (int i = 0; i < 32; i++) {
            if ((A & (1 << i)) != 0) {
                ans++;
            }
        }
        return ans;


    }

}
