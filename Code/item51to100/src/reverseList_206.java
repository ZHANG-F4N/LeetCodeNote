

public class reverseList_206 {
    public static void main(String[] args) {
        int[] head = {1, 2, 3, 4};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        ListNode ans = reverseList(start.next);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode cursor = head.next;
        ListNode temp = null;
        head.next = null;
        while (cursor != null) {
            temp = cursor;
            cursor = cursor.next;
            temp.next = head;
            head = temp;
        }
        return head;
    }
}
