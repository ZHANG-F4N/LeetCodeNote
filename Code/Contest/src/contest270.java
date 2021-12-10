import java.sql.Array;
import java.util.*;
import java.util.stream.IntStream;

public class contest270 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findEvenNumbers(new int[]{2, 1, 3, 0})));

        Integer[] leaf = {5, 1, 2, 3, null, 6, 4};
        TreeNode root = new TreeNode().Build(leaf);
        getDirections(root, 3, 6);
    }

    public static int[] findEvenNumbers(int[] digits) {

        HashSet<Integer> ans = new HashSet<>();
        //boolean[] ints = new boolean[digits.length];

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) continue;
            for (int j = 0; j < digits.length; j++) {
                if (j == i) continue;
                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j) continue;
                    if ((digits[k] & 1) != 0) continue;
                    ans.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
                }
            }
        }
        int[] ints = new int[ans.size()];
        Iterator<Integer> it = ans.iterator();
        int i = 0;
        while (it.hasNext()) {
            ints[i++] = it.next();
        }
        Arrays.sort(ints);
        return ints;

    }

    public ListNode deleteMiddle(ListNode head) {
        if (head == null && head.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head;

        while (fast != null || fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;

        return head;

    }

    public static String getDirections(TreeNode root, int startValue, int destValue) {


        Deque<Character> start = new ArrayDeque<>();
        Deque<Character> dest = new ArrayDeque<>();
        DFS(root, startValue, new Stack<>(), start);
        DFS(root, destValue, new Stack<>(), dest);

        while (start.peekLast() == dest.peekLast()) {
            start.pollLast();
            dest.pollLast();
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < start.size(); i++) {
            ans.append('U');
        }
        while (!dest.isEmpty()) {
            ans.append(dest.pollLast());
        }


        return ans.toString();
    }

    public static void DFS(TreeNode root, int dest, Stack<Character> path, Deque<Character> tar) {
        if (root == null) {
            return;
        }
        if (root.val == dest) {
            Iterator<Character> it = path.iterator();
            while (it.hasNext()) {
                tar.push(it.next());
            }
            return;
        }
        path.push('L');
        DFS(root.left, dest, path, tar);
        path.pop();
        path.push('R');
        DFS(root.right, dest, path, tar);
        path.pop();

    }

}
