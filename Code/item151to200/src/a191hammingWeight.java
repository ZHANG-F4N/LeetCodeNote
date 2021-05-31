public class a191hammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(3));
    }
    public static int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if((n & (1<<i)) != 0){
                ans++;
            }
        }
        return ans;
    }
}
