public class a1342numberOfSteps {
    public static void main(String[] args) {
        System.out.println(numberOfSteps(14));
        System.out.println(numberOfSteps(8));
    }

    public static int numberOfSteps(int num) {
        int ans = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                ans++;
                num--;
            }
            if (num == 0) break;
            ans++;
            num = (num >> 1);
        }
        return ans;
    }
}
