import javax.swing.text.html.HTMLDocument;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class a39largestRectangleArea {
    public static void main(String[] args) {
//        int[] heights = {2, 1, 5, 6, 2, 3};
//        int[] heights = {2,4};
//        int[] heights = {2, 1, 2};
//        int[] heights = {2, 1, 0, 2};
//        int[] heights = {5, 4, 1, 2};
//        int[] heights = {4, 2, 0, 3, 2, 5};
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) new_heights[i] = heights[i - 1];
        for (int i = 0; i < new_heights.length; i++) {
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                ans = Math.max(ans, (i - stack.peek()-1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return ans;
    }
}
