public class a203removeElements {
    public static void main(String[] args) {
        int[] nums = {6, 6, 6, 6};
        ListNode head = new ListNode().build(nums);
        head = removeElements(head.next, 6);
        System.out.println(head);

    }

    public static ListNode removeElements(ListNode head, int val) {
        head = new ListNode(0, head);
        ListNode index = head;
        while (index != null && index.next != null) {
            while (index.next != null && index.next.val == val) {
                index.next = index.next.next;
            }
            if (index.next == null){
                return head.next;
            }
            index = index.next;
        }
        return head.next;
    }
}
