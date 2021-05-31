public class a461hammingDistance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));

    }

    public static int hammingDistance(int x, int y) {
        int ans = 0;
        int temp = x ^ y;
        for (int i = 0; i < 32; i++) {
            if ((temp&1<<i)!=0){
                ans++;
            }
        }
        return ans;
    }
}
