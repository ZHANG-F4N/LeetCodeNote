import java.awt.event.MouseAdapter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class a37asteroidCollision {
    public static void main(String[] args) {
        int[] asteroids = {10, 2, -5};
//        int[] asteroids = {-2, -1, 1, 2};
//        int[] asteroids = {-2, -2, -2};
//        int[] asteroids = {-2, -2, 1, -2};

        System.out.println(Arrays.toString(asteroidCollision(asteroids)));
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (stack.empty()) {
                stack.push(asteroids[i]);
                continue;
            }
            int top = stack.peek();
            if ((top ^ asteroids[i]) >= 0 || (top < 0 && asteroids[i] > 0)) {
                stack.push(asteroids[i]);
                continue;
            }

            boolean collision = false;
            while (!stack.empty() && (top > 0 && asteroids[i] < 0)) {
                if (Math.abs(top) > Math.abs(asteroids[i])) {
                    collision = true;
                    break;
                } else if (Math.abs(top) < Math.abs(asteroids[i])) {
                    stack.pop();
                    collision = false;
                    if (stack.empty()) {
                        break;
                    }
                    top = stack.peek();
                    continue;
                } else {
                    collision = true;
                    stack.pop();
                    break;
                }
            }
            if (!collision) {
                stack.push(asteroids[i]);
            }

        }
        Iterator<Integer> it = stack.iterator();
        int[] ans = new int[stack.size()];
        int i = 0;
        while (it.hasNext()) {
            ans[i++] = it.next();
        }
        return ans;
    }
}
