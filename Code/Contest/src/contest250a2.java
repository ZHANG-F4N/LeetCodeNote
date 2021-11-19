import java.util.HashSet;

public class contest250a2 {
    public static void main(String[] args) {
        System.out.println(addRungs(new int[]{1, 3, 5, 10}, 2));
        System.out.println(addRungs(new int[]{3}, 1));
        System.out.println(addRungs(new int[]{5}, 10));

        System.out.println(addRungs(new int[]{1, 3, 5, 10}, 3));
    }

    public static int addRungs(int[] rungs, int dist) {

        int ans = 0;
        int dis = rungs[0];
        if (dis > dist) {
            ans += (dis - 0.5) / dist;
        }

        for (int i = 1; i < rungs.length; i++) {
            dis = rungs[i] - rungs[i - 1];
            if (dis > dist) {
                ans += (dis - 0.5) / dist;
            }
        }
        return ans;
    }

}
