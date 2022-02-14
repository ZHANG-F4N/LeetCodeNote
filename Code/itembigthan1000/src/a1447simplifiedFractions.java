import java.util.ArrayList;
import java.util.List;

public class a1447simplifiedFractions {
    public static void main(String[] args) {
        System.out.println(simplifiedFractions(6));
        System.out.println(gcd(5,6));
    }


    public static List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(i + "/" + j);
                }
            }
        }
        return ans;
    }

    public static int gcd(int i, int j) {
        // 最大公约数=1
        return j == 0 ? i : gcd(j, i % j);
    }
}
