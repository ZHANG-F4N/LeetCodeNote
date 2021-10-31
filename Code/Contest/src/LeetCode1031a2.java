import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class LeetCode1031a2 {
    public static void main(String[] args) {
        int[] node = {5, 3, 1, 2, 5, 1, 2};
        ListNode head = new ListNode().build(node);
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(head.next)));
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        //head = new ListNode(0, head);
        ArrayList<Integer> list = new ArrayList<>();


        // 0 1 2 3 4 5 6
        // 5 3 1 2 5 1 2
        //     *   * *
        ListNode pre = head;
        ListNode temp = head.next;
        int idx = 1;
        while (temp.next != null) {
            if (pre.val < temp.val && temp.val > temp.next.val) {
                list.add(idx);
            }
            if (pre.val > temp.val && temp.val < temp.next.val) {
                list.add(idx);
            }
            idx++;
            pre = pre.next;
            temp = temp.next;
        }
        if (list.size() < 2) {
            return new int[]{-1, -1};
        }
        int min = Integer.MAX_VALUE;
        int preval = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i) - preval;
            preval = list.get(i);
            min = min > val? val:min;
        }
        int max = list.get(list.size()-1) - list.get(0);
        return new int[]{min,max};
    }
}
