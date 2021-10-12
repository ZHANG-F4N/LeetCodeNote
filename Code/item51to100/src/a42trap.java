import java.awt.event.MouseAdapter;

public class a42trap {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height));
    }

    //对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，
    // 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]。
    //

    public static int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }

        int N = height.length;


        int[] leftMax = new int[N];
        leftMax[0] = height[0];
        int[] rightMax = new int[N];
        rightMax[N - 1] = height[N - 1];
        for (int i = 1; i < N; i++) {
            if (height[i] > leftMax[i - 1]) {
                leftMax[i] = height[i];
            } else {
                leftMax[i] = leftMax[i - 1];
            }

            if (height[N - 1 - i] > rightMax[N - i]) {
                rightMax[N - 1 - i] = height[N - 1 - i];
            } else {
                rightMax[N - 1 - i] = rightMax[N - i];
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            int temp = Math.min(leftMax[i], rightMax[i]) - height[i];
            ans += temp > 0 ? temp : 0;
        }

        return ans;
    }
}
