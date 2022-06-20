import java.util.Arrays;

public class a1051heightChecker {
    public static void main(String[] args) {

    }

    public static int heightChecker(int[] heights) {
        int[] temp = Arrays.copyOf(heights, heights.length);

        Arrays.sort(heights);
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            if (temp[i] != heights[i]) ans++;
        }
        return ans;
    }
}
