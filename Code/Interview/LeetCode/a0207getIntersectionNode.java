public class a0207getIntersectionNode {
    public static void main(String[] args) {
        Integer[] node1 = {2, 3};
        ListNode head = new ListNode().build(node1);
        getIntersectionNode(head.next, head.next.next);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;

    }
}
