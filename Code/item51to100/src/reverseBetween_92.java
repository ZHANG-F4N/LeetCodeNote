import java.util.List;

public class reverseBetween_92 {
    public static void main(String[] args) {
        int[] head = { 3,5};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        int left = 1, right = 2;
        ListNode ans = reverseBetween(start.next, left, right);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right || head.next == null) {
            return head;
        }
        ListNode reverseHead = head;
        ListNode temp = head;
        int tempNum = left - 1;
        while (tempNum-- > 0) {
            reverseHead = temp;
            temp = temp.next;
        }
        ListNode cursor = reverseHead.next;
        reverseHead.next = cursor;
        cursor = cursor.next;
        reverseHead.next.next = null;
        ListNode joinPoint = reverseHead.next;
        tempNum = right - left;
        while (tempNum-- > 0 && cursor.next != null) {
            temp = cursor;
            cursor = cursor.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
        }
        joinPoint.next = cursor;
        //System.out.println("===>" + cursor.val);
        return head;
    }
}
