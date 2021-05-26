public class rotateRight_61 {
    public static void main(String[] args) {
        int[] head = {0, 1, 2};
        ListNode start = new ListNode(0);
        ListNode end = start;
        for (int i : head) {
            ListNode temp = new ListNode(i, null);
            end.next = temp;
            end = temp;
        }
        int k = 4;
        ListNode ans = rotateRight(start.next, k);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        int listLength = 0;
        ListNode tour = head;
        while (tour != null) {
            tour = tour.next;
            listLength++;
        }
        if (listLength <= 1) {
            return head;
        }
        tour = head;
        k = listLength - (k % listLength);
        if (k == listLength) {
            return head;
        }
        while (--k > 0) {
            tour = tour.next;
        }
        ListNode temp = tour.next;
        tour.next = null;
        tour = temp;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head;
        head = tour;
        return head;
    }
}