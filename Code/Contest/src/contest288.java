import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class contest288 {
    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{6, 3, 3, 2}, 2));
//        System.out.println(minimizeResult("247+38"));
//        System.out.println(largestInteger("65875"));
//        System.out.println(largestInteger(1234));
    }

    //Q1
    public static int largestInteger(int num) {
        int[] map = new int[10];
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        int temp = num;
        int i = 9;
        while (temp != 0) {
            if ((temp & 1) == 0) {
                map[i] = 1;
                even.add(temp % 10);
            } else {
                map[i] = -1;
                odd.add(temp % 10);
            }
            temp /= 10;
            i--;
        }
        Collections.sort(odd, (o1, o2) -> o2 - o1);
        Collections.sort(even, (o1, o2) -> o2 - o1);
        int ans = 0;
        for (int j = 0; j < 10; j++) {
            if (map[j] == 0) continue;
            ans *= 10;
            if (map[j] == 1) {
                ans += even.get(0);
                even.remove(0);
            } else {
                ans += odd.get(0);
                odd.remove(0);
            }
        }


        return ans;
    }

    //Q2
    public static String minimizeResult(String expression) {

        String[] split = expression.split("\\+");
        String f = split[0];
        String b = split[1];
        int l = 0, r = 1;

        String ans = "";
        int n = f.length();
        int m = b.length();
        int ansVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int val = 0;
            for (int j = 0; j < m; j++) {
                String num11 = f.substring(0, i);
                int num1 = num11.equals("") ? 1 : Integer.parseInt(num11);
                String num22 = f.substring(i);
                int num2 = num22.equals("") ? 1 : Integer.parseInt(num22);
                String num33 = b.substring(0, j + 1);
                int num3 = num33.equals("") ? 1 : Integer.parseInt(num33);
                String num44 = b.substring(j + 1);
                int num4 = num44.equals("") ? 1 : Integer.parseInt(num44);
                val = num1 * (num2 + num3) * num4;
                if (val < ansVal) {
                    ansVal = val;
                    ans = num11 + "(" + num22 + "+" + num33 + ")" + num44;
                }
            }
        }

        return ans;
    }

    //Q3
    public static int maximumProduct(int[] nums, int k) {
        if (nums.length == 1) return nums[0] + k;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int num : nums) {
            queue.offer(num);
        }
        while (k != 0) {
            int top = queue.poll();
            if (top == queue.peek()) {
                queue.offer(queue.peek() + 1);
                k--;
            } else if (queue.peek() - top <= k) {
                queue.offer(queue.peek());
                k -= queue.peek() - top;
            } else {
                queue.offer(top + k);
                k = 0;
            }
        }

        long ans = 1;
        while (!queue.isEmpty()) {
            ans = ans * queue.poll() % 1000000007;
        }

        return (int) ans;


    }
}
