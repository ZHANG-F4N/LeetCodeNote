public class a0202kthToLast {
    public static void main(String[] args) {
        Integer[] node = {1, 2, 3, 4, 5};
        ListNode head = new ListNode().build(node);
        System.out.println(kthToLast(head.next, 2));

    }

    public static int kthToLast(ListNode head, int k) {

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.val;

    }
}
